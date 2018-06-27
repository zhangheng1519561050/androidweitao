package com.example.frament;

import com.example.androidweitao.Img1;
import com.example.androidweitao.Img12;
import com.example.androidweitao.R;
import com.example.androidweitao.R.id;
import com.example.androidweitao.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class BBFragment1 extends Fragment {
	private Button img11;
	private Button img12;
	private Button img13;
	private Button img14;
	private Button img15;
	private Button img16;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.baibao1, null);
		img11=(Button) view.findViewById(R.id.img11);
		img12=(Button)view.findViewById(R.id.img12);
		img13=(Button)view.findViewById(R.id.img13);
		img14=(Button)view.findViewById(R.id.img14);
		img15=(Button)view.findViewById(R.id.img15);
		img16=(Button)view.findViewById(R.id.img16);
		
		img16.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "����δ��ͨ ��Ҳ���¸��汾���еģ����Ǻ�Ŭ��", 200).show();	
			}
		});
		img15.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "����δ��ͨ ��Ҳ���¸��汾���еģ����Ǻ�Ŭ��", 200).show();
				
			}
		});
	    img14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "����Ѿ����ˣ���Ϊ��̫����", 200).show();
				
			}
		});
		img13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "�eҡ�ˣ��¸��汾�Ż���Ŷ", 200).show();
				
			}
		});
		
		img12.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(getActivity(),Img12.class);
				startActivity(intent);
				
			}
		});
		img11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(), Img1.class);
				startActivity(intent);
			}
		});
		return view;

	}

}
