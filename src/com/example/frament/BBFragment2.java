package com.example.frament;

import com.example.androidweitao.Img22;
import com.example.androidweitao.Img24;
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
   
    
public class BBFragment2 extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.baibao2, null);
		Button img22;
		Button img24;
		img22=(Button) view.findViewById(R.id.img22);
		img24=(Button) view.findViewById(R.id.img24);
		
		img24.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),Img24.class);
				startActivity(intent);
				
			}
		});
 		img22.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				Intent intent = new Intent(getActivity(),Img22.class);
				startActivity(intent);
				
				
			}
		});

		return view;

	}

}
