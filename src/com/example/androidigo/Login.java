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
import com.example.androidweitao.R;
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
		// ��ȡ�����������༭��
		etName = (EditText) findViewById(R.id.userName);
		etPassword = (EditText) findViewById(R.id.userPassword);
		// ��ȡ�����е�������ť
		btnlogin = (Button) findViewById(R.id.btnlogin);
	  btncancel = (Button) findViewById(R.id.btncancel);
	  btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Ҫ���ĸ�Activity����ԭ���Ǹ���Ƭ �ڶ�������������Ƭ���ڵ�Activity
				Intent intent = new Intent(Login.this, AllFragment.class);

				//intent.putExtra("pageid", 1);

				// ��������
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

	// // ���巢������ķ���
	// private JSONObject Query(String username, String password) throws
	// Exception {
	// // ʹ��Map��װ����
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("user", username);
	// map.put("password", password);
	// // ���巢�� �����URL
	// String url = HttpUtil.BASE_URL + "login.jsp";
	// // ��������
	// return new JSONObject(HttpUtil.postRequest(url, map));
	// }

	// ���û�������û������������У��
	private boolean validate() {
		String username = etName.getText().toString().trim();
		if (username.equals("")) {
			Toast.makeText(Login.this, "�û��˻��Ǳ����", 200).show();
			return false;
		}
		String password = etPassword.getText().toString().trim();
		if (password.equals("")) {
			Toast.makeText(Login.this, "�û������Ǳ����", 200).show();
			return false;
		}
		return true;
	}

	public void getUser() {
		String url = "http://172.16.62.136:8080/WebIGo/userloginforandroid.do?userName="
				+ name + "&userPassword=" + pass + "";
		String result = "";

		// android ���ʷ������˵����
		HttpClient client = new DefaultHttpClient();

		// �������� ��Ϊ get post
		HttpGet getMethod = new HttpGet(url);

		// ִ��get����
		try {
			HttpResponse response = client.execute(getMethod);
			// ���շ���ֵ
			if (response.getStatusLine().getStatusCode() == 200) {
				// response.getEntity() ���Ƿ������˵ķ���ֵ
				// EntityUtils.toString ���������ֵת��Ϊ String
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
				Toast.makeText(Login.this, "�û������ڣ�����ע�ᣡ", 200).show();
			} else if (result.equals("1")) {
				//Toast.makeText(Login.this, "���ڵ�¼��", 200).show();
				Intent intent = new Intent(Login.this, AllFragment.class);
				startActivity(intent);
			} else if (result.equals("2")) {
				Toast.makeText(Login.this, "������������³��룡", 200).show();
			}else{
				Toast.makeText(Login.this, "δ֪����", 200).show();
			}
		}

		return false;
	}
}
