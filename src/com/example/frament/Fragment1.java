package com.example.frament;


import com.example.androidweitao.Login;
import com.example.androidweitao.R;
import com.example.androidweitao.Register;
import com.example.androidweitao.R.id;
import com.example.androidweitao.R.layout;

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
		// Toast��ӡ
		login = (Button) view.findViewById(R.id.btn_login);
		register = (Button) view.findViewById(R.id.register);
		flipper.startFlipping();
		// ע����ת
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ��תҳ��
				Intent intent = new Intent(getActivity(), Register.class);

				// 2��������ʽ �ڶ�����Ϊ�˱�����һ�ε�¼�ۼ�
				startActivity(intent);

			}
		});

		// ��¼��ת
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// ��תҳ��
				Intent intent = new Intent(getActivity(), Login.class);

				// 2��������ʽ �ڶ�����Ϊ�˱�����һ�ε�¼�ۼ�
				startActivity(intent);
			}
		});
		return view;

	}

	

}
