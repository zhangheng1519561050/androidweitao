package com.example.frament;

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

import com.example.adapters.shoppingcartAdapter;
import com.example.androidweitao.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

//购物车
public class Fragment4 extends Fragment implements Handler.Callback  {
	private Handler handler;
	private JSONArray arrayData;
	private Map<String, shoppingproductImg> images;
	private ListView shoppingcartlistview;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.shoppingcartlistview, null);

		shoppingcartlistview = (ListView) view
				.findViewById(R.id.shoppingcart_listview);
		images = new HashMap<String, shoppingproductImg>();
		// 启动子线程
		handler = new Handler(this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				String userName = getActivity().getSharedPreferences(
						"userName", Activity.MODE_WORLD_READABLE).getString(
						"userName", "123");
				getshoppingInfoemation(userName);
			}
		}).start();
		

		return view;
	}

	private void getshoppingInfoemation(String userName) {
		// TODO Auto-generated method stub
		String url = "http://192.168.191.1:8080/WebIGo/shoppingcartforandroid.do?userName="+userName;
		String result = "";
		// android 访问服务器端的组件
		HttpClient client = new DefaultHttpClient();
		// 访问请求 分为 get post
		HttpGet getMethod = new HttpGet(url);
		try {
			HttpResponse response = client.execute(getMethod);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getshoppingImage(int pid) {
		String url = "http://192.168.191.1:8080/Weitao/gettypeimageforandroid.do?id="
				+ pid;

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
				shoppingproductImg pi = new shoppingproductImg();
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

	public boolean handleMessage(Message msg) {
		String message = msg.obj.toString();
		try {
			arrayData = new JSONArray(message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (msg.what == 1) {
//			try {
//				arrayData = new JSONArray(message);
//				for (int i = 0; i < arrayData.length(); i++) {
//					JSONObject object = arrayData.getJSONObject(i);
//					final int pid = i+1;
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							getshoppingImage(pid);
//						}
//					}).start();
//				}
				shoppingcartAdapter adapter = new shoppingcartAdapter(
						getActivity(), arrayData);
				shoppingcartlistview = (ListView) shoppingcartlistview
						.findViewById(R.id.shoppingcart_listview);
				shoppingcartlistview.setAdapter(adapter);

//			} catch (JSONException e) {
//
//				e.printStackTrace();
//			}
		} else {
			shoppingproductImg pi = (shoppingproductImg) msg.obj;
			images.put(String.valueOf(pi.id), pi);
			if (images.size() == arrayData.length()) {
				// 建立适配器
				shoppingcartAdapter adapter = new shoppingcartAdapter(
						getActivity(), arrayData, images);
				// 设置数据适配器
				shoppingcartlistview = (ListView) shoppingcartlistview
						.findViewById(R.id.shoppingcart_listview);
				shoppingcartlistview.setAdapter(adapter);
			}

		}
		return false;
	}

	public class shoppingproductImg {
		public int id;
		public Bitmap img;
	}
}
