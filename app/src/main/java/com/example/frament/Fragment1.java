package com.example.frament;


import com.example.androidweitao.Login;
import com.example.androidweitao.R;
import com.example.androidweitao.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

public class Fragment1 extends Fragment {
	private Button register;
	private Button login;
	private ViewFlipper flipper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.f1, null);
		flipper = (ViewFlipper) view.findViewById(R.id.details);
		// Toast打印
		login = (Button) view.findViewById(R.id.btn_login);
		register = (Button) view.findViewById(R.id.register);
		flipper.startFlipping();
		// 注册跳转
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 跳转页面
				Intent intent = new Intent(getActivity(), Register.class);

				// 2种启动方式 第二种是为了保留上一次登录痕迹
				startActivity(intent);

			}
		});

		// 登录跳转
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 跳转页面
				Intent intent = new Intent(getActivity(), Login.class);

				// 2种启动方式 第二种是为了保留上一次登录痕迹
				startActivity(intent);
			}
		});
		return view;

	}

	

}
