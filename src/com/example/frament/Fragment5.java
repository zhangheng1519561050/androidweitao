package com.example.frament;

import java.util.ArrayList;
import java.util.List;

import com.example.adapters.MyPagerAdapter;
import com.example.androidweitao.R;
import com.example.androidweitao.R.id;
import com.example.androidweitao.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Fragment5 extends Fragment {
	private ViewPager pagger2;
	private RadioButton bbx_btn1;
	private RadioButton bbx_btn2;
	private RadioButton bbx_btn3;
	private RadioGroup btnGroup2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f5, null);
		btnGroup2 = (RadioGroup) view.findViewById(R.id.radioGroup2);
		bbx_btn1 = (RadioButton) view.findViewById(R.id.bbx_btn1);
		bbx_btn2 = (RadioButton) view.findViewById(R.id.bbx_btn2);
		bbx_btn3 = (RadioButton) view.findViewById(R.id.bbx_btn3);
		pagger2 = (ViewPager) view.findViewById(R.id.viewpager2);
		// ��������
		List<Fragment> datas = new ArrayList<Fragment>();

		BBFragment1 bbfra1 = new BBFragment1();
		// ����Ƭ����һЩ����
		Bundle bundle = new Bundle();
		bundle.putString("index", "1");
		bbfra1.setArguments(bundle);

		BBFragment2 bbfra2 = new BBFragment2();
		// ����Ƭ����һЩ����
		Bundle bundle2 = new Bundle();
		bundle.putString("index", "2");
		bbfra2.setArguments(bundle);

		BBFragment3 bbfra3 = new BBFragment3();
		// ����Ƭ����һЩ����
		Bundle bundle3 = new Bundle();
		bundle.putString("index", "3");
		bbfra3.setArguments(bundle);
		
		
		datas.add(bbfra1);
		datas.add(bbfra2);
		datas.add(bbfra3);
		
		MyPagerAdapter adapter=new MyPagerAdapter(getChildFragmentManager(), datas);
//		MyPagerAdapter adapter = new MyPagerAdapter(
//				getSupportFragmentManager(), datas);

		// ����������
		pagger2.setAdapter(adapter);

		// ������ʾ�ڼ�ҳ
		pagger2.setCurrentItem(0);


		pagger2.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
		 
				RadioButton radioButton=(RadioButton) btnGroup2.getChildAt(position);
				radioButton.setChecked(true);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		bbx_btn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger2.setCurrentItem(0);
				}
			}
		});
		bbx_btn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger2.setCurrentItem(1);
				}
			}
		});
		bbx_btn3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger2.setCurrentItem(2);
				}
			}
		});
		return view;
		
		
	

	}

}
