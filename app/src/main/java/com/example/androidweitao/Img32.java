package com.example.androidweitao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RatingBar;
import android.widget.Toast;

public class Img32 extends Activity {
	private RatingBar rb_main_rating;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img32);
		//获得RatingBar的控件
		rb_main_rating = (RatingBar) findViewById(R.id.rb_main_rating);
		//给控件设置监听事件
		rb_main_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				Toast t=Toast.makeText(Img32.this,"谢谢您的参与，     " +
						"您的评分为："+rating,Toast.LENGTH_SHORT);
				t.setGravity(Gravity.CENTER,0,0);
				t.show();
			}
		});
	}
}
