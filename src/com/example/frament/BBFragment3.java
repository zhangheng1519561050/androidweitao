package com.example.frament;

import com.example.androidweitao.Img33;
import com.example.androidweitao.Img34;
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
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.Toast;

public class BBFragment3 extends Fragment {
	
	private Button img35;
	private Button img33;
	private Button img34;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.baibao3, null);
		img35=(Button) view.findViewById(R.id.img35);
		img33=(Button) view.findViewById(R.id.img33);
		img34=(Button) view.findViewById(R.id.img34);
		
		img34.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getActivity(),Img34.class);
				startActivity(intent);
				
			}
		});
		
		img33.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),Img33.class);
				startActivity(intent);
			}
		});
		img35.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "�Ѿ������°汾��", 200).show();
				
			}
		});

		return view;

	}

}
