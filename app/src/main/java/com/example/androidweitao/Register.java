package com.example.androidweitao;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements Handler.Callback {
	private EditText userName;
	private EditText email;
	private static EditText password;
	private static EditText repassword;
	private static CheckBox regshowpassword = null;
	private CheckBox checkagree;
	private Button register;
	private TextView Hottopic;
	private Handler handler;
	private String name;
	private String pass;
	private String Email;
	private String repass;
	private boolean check;
	private TextView zhucexieyi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		userName = (EditText) findViewById(R.id.userName);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		repassword = (EditText) findViewById(R.id.repassword);
		regshowpassword = (CheckBox) findViewById(R.id.regshowpassword);
		zhucexieyi = (TextView) findViewById(R.id.zhucexieyi);
		handler = new Handler(Register.this);
		regshowpassword
				.setOnClickListener((OnClickListener) new OnclickListenerImp());
		checkagree = (CheckBox) findViewById(R.id.checkagree);
		register = (Button) findViewById(R.id.register);
		// checkagree.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		// // TODO Auto-generated method stub
		// }
		// });
		zhucexieyi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Register.this, Zhucexieyi.class);
				startActivity(intent);
			}
		});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name = userName.getText().toString();
				pass = password.getText().toString();
				Email = email.getText().toString();
				repass = repassword.getText().toString();
				check = checkagree.getText().toString() != null;

				// if(name.equals("")||pass.equals("")||Email.equals("")||repass.equals(""))
				if (name.equals("")) {
					Toast.makeText(Register.this, "用户名不能为空！", 200).show();
				} else if (Email.equals("")) {
					Toast.makeText(Register.this, "邮箱不能为空！", 200).show();
				} else if (pass.equals("")) {
					Toast.makeText(Register.this, "用户密码不能为空！", 200).show();
				} else if (repass.equals("")) {
					Toast.makeText(Register.this, "重复密码不能为空！", 200).show();
				} else if (!pass.equals(repass)) {
					Toast.makeText(Register.this, "两次密码输入不同，请重新输入！", 200)
							.show();
				} else {
					new Thread(new Runnable() {

						@Override
						public void run() {

							getResult();
						}

					}).start();
				}

			}
		});

		// 注册协议跳转
		// Hottopic = (TextView) findViewById(R.id.zhucexieyi);

		// 链接跳转
		// Hottopic.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private void getResult() {
		// 必须注意语句
		String url = "http://192.168.191.1:8080/Weitao/registerforandroid.do?userName="
				+ name + "&userPassword=" + pass + "&userEmail=" + Email + "";
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

	public boolean handleMessage(Message msg) {
		String registerchoose = msg.obj.toString();

		String result = "";
		try {
			JSONObject obj = new JSONObject(registerchoose);
			result = obj.getString("registerchoose");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (msg.what == 1) {
			if (result.equals("1")) {
				Intent intent = new Intent(Register.this, Login.class);
				intent.putExtra("loginName", name);
				startActivity(intent);
			} else {
				Toast.makeText(Register.this, "用户已存在", 200).show();
			}
		}
		return false;
	}

	private class OnclickListenerImp implements OnClickListener {

		public void onClick(View v) {

			if (Register.regshowpassword.isChecked()) {
				// 设置为明文显示
				Register.password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
				Register.repassword
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				// 设置为秘闻显示
				Register.password
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
				Register.repassword
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
			}
		}
	}
}
