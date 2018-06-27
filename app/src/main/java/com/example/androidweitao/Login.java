package com.example.androidweitao;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements Handler.Callback {
	private EditText etName;
	private EditText etPassword;
	private Button btnlogin;
	private Button btncancel;
	private Handler handler;
	private JSONArray arrayData;
	private String name;
	private String pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		handler = new Handler(Login.this);
		// 获取界面中两个编辑框
		etName = (EditText) findViewById(R.id.userName);
		etPassword = (EditText) findViewById(R.id.userPassword);
		// 获取界面中的两个按钮
		btnlogin = (Button) findViewById(R.id.btnlogin);
	  btncancel = (Button) findViewById(R.id.btncancel);
	  btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 要从哪个Activity跳回原来那个碎片 第二个参数就是碎片所在的Activity
				Intent intent = new Intent(Login.this, AllFragment.class);

				//intent.putExtra("pageid", 1);

				// 设置数据
			//	setResult(1, intent);

				finish();
			}
		});
		btnlogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = etName.getText().toString();
				pass = etPassword.getText().toString();
				if (validate()) {
					if (!name.equals("") && !pass.equals("")) {

						new Thread(new Runnable() {
							@Override
							public void run() {
								getUser();
							}

						}).start();
					}
				}
			}

		});

	}

	// // 定义发送请求的方法
	// private JSONObject Query(String username, String password) throws
	// Exception {
	// // 使用Map封装请求
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("user", username);
	// map.put("password", password);
	// // 定义发送 请求的URL
	// String url = HttpUtil.BASE_URL + "login.jsp";
	// // 发送请求
	// return new JSONObject(HttpUtil.postRequest(url, map));
	// }

	// 对用户输入的用户名、密码进行校验
	private boolean validate() {
		String username = etName.getText().toString().trim();
		if (username.equals("")) {
			Toast.makeText(Login.this, "用户账户是必填项！", 200).show();
			return false;
		}
		String password = etPassword.getText().toString().trim();
		if (password.equals("")) {
			Toast.makeText(Login.this, "用户密码是必填项！", 200).show();
			return false;
		}
		return true;
	}

	public void getUser() {
		String url = "http://localhost:192.168.191.1/Weitao/userloginforandroid.do?userName="
				+ name + "&userPassword=" + pass + "";
		String result = "";

		// android 访问服务器端的组件
		HttpClient client = new DefaultHttpClient();

		// 访问请求 分为 get post
		HttpGet getMethod = new HttpGet(url);

		// 执行get请求
		try {
			HttpResponse response = client.execute(getMethod);
			// 接收返回值
			if (response.getStatusLine().getStatusCode() == 200) {
				// response.getEntity() 就是服务器端的返回值
				// EntityUtils.toString 把这个返回值转换为 String
				result = EntityUtils.toString(response.getEntity(), "utf-8");
				Message msg = new Message();
				msg.what = 1;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean handleMessage(Message msg) {
		String logchoose = msg.obj.toString();
		String result = "";
		try {
			JSONObject obj = new JSONObject(logchoose);
			result = obj.getString("logchoose");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (msg.what == 1) {
			if (result.equals("0")) {
				Toast.makeText(Login.this, "用户不存在，请先注册！", 200).show();
			} else if (result.equals("1")) {
				//Toast.makeText(Login.this, "正在登录…", 200).show();
				Intent intent = new Intent(Login.this, AllFragment.class);
				startActivity(intent);
			} else if (result.equals("2")) {
				Toast.makeText(Login.this, "密码错误，请重新出入！", 200).show();
			}else{
				Toast.makeText(Login.this, "未知错误", 200).show();
			}
		}

		return false;
	}
}
