package com.example.androidweitao;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.ImageView;

public class IGoActivity extends Activity {
	private ImageView homepage;

	// private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.igo);
		// btn =(Button)findViewById(R.id.textjrtj);
		//������Ϣ
		SharedPreferences sharp = getSharedPreferences("config",
				Activity.MODE_PRIVATE);
		// �ٽ��б༭ ��ȡ����ļ��ı༭�� edit()���������ҿ�ʼ���ļ����б༭��
		Editor editor = sharp.edit();

		editor.putInt("logchoose", 0);
		editor.commit();
		//����2����ת
		final Intent it = new Intent(IGoActivity.this, AllFragment.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				startActivity(it);
				finish();
			}
		};
		timer.schedule(task, 1000 * 1);

		// btn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// Intent intent=new Intent();
		// }
		// });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.igo_order, menu);
		return true;
	}

}
