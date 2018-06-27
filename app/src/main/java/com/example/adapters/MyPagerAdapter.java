package com.example.adapters;


import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> list;

	public MyPagerAdapter(FragmentManager fm, List<Fragment> datalist) {
		super(fm);
		this.list = datalist;
	}

	// 相当于listview的getView
	@Override
	public Fragment getItem(int arg0) {

		return list.get(arg0);
	}

	// 返回几页
	@Override
	public int getCount() {
		return list.size();
	}

}
