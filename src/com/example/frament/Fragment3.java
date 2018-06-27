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
import org.json.JSONObject;
import com.example.adapters.ClassificationAdapter;
import com.example.androidweitao.R;
import com.example.androidweitao.R.id;
import com.example.androidweitao.R.layout;
import com.example.androidweitao.productActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment3 extends Fragment implements Handler.Callback {
	private Handler handler;
	private JSONArray arrayData;
	private Map<String, ClassificationImg> images;
	private ListView Classificationlistview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classificationlistviewf3);
		images = new HashMap<String, ClassificationImg>();
		// �������߳�
		handler = new Handler(this);

		new Thread(new Runnable() {
			int producttype;

			@Override
			public void run() {
				getUser(producttype);
			}
		}).start();
	}

	private void setContentView(int classificationlistviewf3) {

	}

	public void getUser(int pid) {

		String url = "http://172.16.62.136:8080/WebIGo/firsttypeforandroid.do?id="
				+ pid;
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

	private void getImage(int pid) {
		String url = "http://172.16.62.136:8080/WebIGo/gettypeimageforandroid.do?id="
				+ pid;

		// android ���ʷ������˵����
		HttpClient client = new DefaultHttpClient();
		// �������� ��Ϊ get post
		HttpGet getMethod = new HttpGet(url);
		// ִ��get����
		try {
			HttpResponse response = client.execute(getMethod);
			// ���շ���ֵ
			if (response.getStatusLine().getStatusCode() == 200) {

				InputStream stream = response.getEntity().getContent();
				Message msg = new Message();
				msg.what = 2;
				Bitmap bitmap = BitmapFactory.decodeStream(stream);
				ClassificationImg pi = new ClassificationImg();
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

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.classificationlistviewf3, null);

		Classificationlistview = (ListView) view
				.findViewById(R.id.Classificationlistview);
		Classificationlistview
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						Intent intent = new Intent(getActivity(),
								productActivity.class);
						// ��ȡlistviewλ��
						JSONObject object;
						try {
							object = arrayData.getJSONObject(position);
							intent.putExtra("position", object.getString("producttypeId"));
							startActivity(intent);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}
				});
		return view;
	}

	@Override
	public boolean handleMessage(Message msg) {
		String message = msg.obj.toString();
		if (msg.what == 1) {
			try {
				arrayData = new JSONArray(message);

				for (int i = 0; i < arrayData.length(); i++) {
					JSONObject object = arrayData.getJSONObject(i);
					final int pid = object.getInt("producttypeId");
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
			ClassificationImg pi = (ClassificationImg) msg.obj;
			images.put(String.valueOf(pi.id), pi);
			if (images.size() == arrayData.length()) {
				// ����������
				ClassificationAdapter adapter = new ClassificationAdapter(
						getActivity(), arrayData, images);
				// ��������������
				Classificationlistview = (ListView) Classificationlistview
						.findViewById(R.id.Classificationlistview);
				Classificationlistview.setAdapter(adapter);

			}

		}
		return false;
	}

	public class ClassificationImg {
		public int id;
		public Bitmap img;
	}
}
