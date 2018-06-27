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

public class WeitaoActivity extends Activity {
	private ImageView homepage;

	// private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weitao);
		// btn =(Button)findViewById(R.id.textjrtj);
		//保存信息
		SharedPreferences sharp = getSharedPreferences("config",
				Activity.MODE_PRIVATE);
		// 再进行编辑 获取这个文件的编辑器 edit()方法代表我开始对文件进行编辑了
		Editor editor = sharp.edit();

		editor.putInt("logchoose", 0);
		editor.commit();
		//设置2秒跳转
		final Intent it = new Intent(WeitaoActivity.this, AllFragment.class);
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
