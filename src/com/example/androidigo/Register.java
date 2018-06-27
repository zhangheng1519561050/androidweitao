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
import org.w3c.dom.Document;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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
					Toast.makeText(Register.this, "�û�������Ϊ�գ�", 200).show();
				} else if (Email.equals("")) {
					Toast.makeText(Register.this, "���䲻��Ϊ�գ�", 200).show();
				} else if (pass.equals("")) {
					Toast.makeText(Register.this, "�û����벻��Ϊ�գ�", 200).show();
				} else if (repass.equals("")) {
					Toast.makeText(Register.this, "�ظ����벻��Ϊ�գ�", 200).show();
				} else if (!pass.equals(repass)) {
					Toast.makeText(Register.this, "�����������벻ͬ�����������룡", 200)
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

		// ע��Э����ת
		// Hottopic = (TextView) findViewById(R.id.zhucexieyi);

		// ������ת
		// Hottopic.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private void getResult() {
		// ����ע�����
		String url = "http://172.16.62.136:8080/WebIGo/registerforandroid.do?userName="
				+ name + "&userPassword=" + pass + "&userEmail=" + Email + "";
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
				Toast.makeText(Register.this, "�û��Ѵ���", 200).show();
			}
		}
		return false;
	}

	private class OnclickListenerImp implements OnClickListener {

		public void onClick(View v) {

			if (Register.regshowpassword.isChecked()) {
				// ����Ϊ������ʾ
				Register.password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
				Register.repassword
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
			} else {
				// ����Ϊ������ʾ
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
