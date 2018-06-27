package com.example.androidweitao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapters.productAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class productActivity extends Activity implements Handler.Callback {

	private Handler handler;
	private JSONArray arrayData;
	private Map<String, productImg> images;
	private ListView productlist;
	private Button back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productlist);
		images = new HashMap<String, productImg>();
		back=(Button) findViewById(R.id.fanhui);
		// 启动子线程
		handler = new Handler(productActivity.this);

		new Thread(new Runnable() {
			 Intent intent =getIntent();
			 Integer position = Integer.parseInt(intent.getStringExtra("position"));
			@Override
			public void run() {
				getUser(position);
			}
		}).start();
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 要从哪个Activity跳回原来那个碎片 第二个参数就是碎片所在的Activity
				Intent intent = new Intent(productActivity.this, AllFragment.class);

//				intent.putExtra("pageid", 1);
//
//				// 设置数据
//				setResult(1, intent);

				finish();
			}
		});
	

	}

	
	
	public void getUser(int pid) {

		String url = "http://192.168.191.1:8080/Weitao/productforandroid.do?id="
				+ pid;
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

	private void getImage(int pid) {
		String url = "http://192.168.191.1:8080/Weitao/getimageforandroid.do?id="
				+ pid;
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

				InputStream stream = response.getEntity().getContent();
				Message msg = new Message();
				msg.what = 2;
				Bitmap bitmap = BitmapFactory.decodeStream(stream);
				productImg pi = new productImg();
				pi.id = pid;
				pi.img = bitmap;
				msg.obj = pi;
				handler.sendMessage(msg);
			}
			// Toast.makeText(UserActivity.this, result, 200).show();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		String message = msg.obj.toString();
		if (msg.what == 1) {
			try {
				arrayData = new JSONArray(message);

				for (int i = 0; i < arrayData.length(); i++) {
					JSONObject object = arrayData.getJSONObject(i);
					final int pid = object.getInt("productId");
					new Thread(new Runnable() {
						@Override
						public void run() {

							getImage(pid);
						}
					}).start();
				}

			} catch (JSONException e) {

				e.printStackTrace();
			}
		} else {
			productImg pi = (productImg) msg.obj;
			images.put(String.valueOf(pi.id), pi);
			if (images.size() == arrayData.length()) {
				// 建立适配器
				productAdapter adapter = new productAdapter(
						productActivity.this, arrayData, images);
				// 设置数据适配器
				productlist = (ListView) findViewById(R.id.productlist);
				productlist.setAdapter(adapter);
			}
		}
		return false;
	}

	public class productImg {
		public int id;
		public Bitmap img;
	}
}
