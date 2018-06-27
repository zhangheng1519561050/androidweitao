package com.example.androidweitao;

import java.util.ArrayList;
import java.util.List;
import com.example.adapters.MyPagerAdapter;
import com.example.frament.Fragment1;
import com.example.frament.Fragment2;
import com.example.frament.Fragment3;
import com.example.frament.Fragment4;
import com.example.frament.Fragment5;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AllFragment extends FragmentActivity implements OnClickListener {
	private ViewPager pagger;
	private RadioButton btn1;
	private RadioButton btn2;
	private RadioButton btn3;
	private RadioButton btn4;
	private RadioButton btn5;
	private RadioGroup btnGroup;
	private int currentindx = 0;
	private int width;
	private ImageView imgmove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitymain);
		btnGroup=(RadioGroup) findViewById(R.id.radioGroup);
		btn1 = (RadioButton) findViewById(R.id.btn1);
		btn2 = (RadioButton) findViewById(R.id.btn2);
		btn3 = (RadioButton) findViewById(R.id.btn3);
		btn4 = (RadioButton) findViewById(R.id.btn4);
		btn5 = (RadioButton) findViewById(R.id.btn5);
		pagger = (ViewPager) findViewById(R.id.viewpager);

		// 构建数据
		List<Fragment> datas = new ArrayList<Fragment>();

		Fragment1 fra1 = new Fragment1();
		// 给碎片传递一些参数
		Bundle bundle = new Bundle();
		bundle.putString("index", "1");
		fra1.setArguments(bundle);

		Fragment2 fra2 = new Fragment2();
		// 给碎片传递一些参数
		Bundle bundle2 = new Bundle();
		bundle.putString("index", "2");
		fra2.setArguments(bundle);

		Fragment3 fra3 = new Fragment3();
		// 给碎片传递一些参数
		Bundle bundle3 = new Bundle();
		bundle.putString("index", "3");
		fra3.setArguments(bundle);

		Fragment4 fra4 = new Fragment4();
		// 给碎片传递一些参数
		Bundle bundle4 = new Bundle();
		bundle.putString("index", "4");
		fra4.setArguments(bundle);

		Fragment5 fra5 = new Fragment5();
		// 给碎片传递一些参数
		Bundle bundle5 = new Bundle();
		bundle.putString("index", "5");
		fra5.setArguments(bundle);

		datas.add(fra1);
		datas.add(fra2);
		datas.add(fra3);
		datas.add(fra4);
		datas.add(fra5);

		MyPagerAdapter adapter = new MyPagerAdapter(
				getSupportFragmentManager(), datas);

		// 设置适配器
		pagger.setAdapter(adapter);

		// 设置显示第几页
		pagger.setCurrentItem(0);


		pagger.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
		 
				RadioButton radioButton=(RadioButton) btnGroup.getChildAt(position);
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

		btn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger.setCurrentItem(0);
				}
			}
		});
		btn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger.setCurrentItem(1);
				}
			}
		});
		btn3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger.setCurrentItem(2);
				}
			}
		});
		btn4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger.setCurrentItem(3);
				}
			}
		});
		btn5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					pagger.setCurrentItem(4);
				}
			}
		});

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			pagger.setCurrentItem(0);
			break;
		case R.id.btn2:
			pagger.setCurrentItem(1);
			break;
		case R.id.btn3:
			pagger.setCurrentItem(2);
			break;
		case R.id.btn4:
			pagger.setCurrentItem(3);
			break;
		case R.id.btn5:
			pagger.setCurrentItem(4);
			break;
		default:
			break;
		}

	}

	
//	public void buttonClick(View button) {
//		Animation anim = AnimationUtils.loadAnimation(AllFragment.this,
//				R.anim.yidong);
//		imgmove.startAnimation(anim);
//
//	};

	// onActivityResult方法 当返回页面时调用这个方法
	// ----------?????
//	@Override
//	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
//		super.onActivityResult(arg0, arg1, arg2);
//		if (arg1 == 1) {
//			if (arg2.getIntExtra("pageid", 0) == 1) {
//				pagger.setCurrentItem(3);
//			}
//			if (arg2.getIntExtra("pageid", 0) == 2) {
//				pagger.setCurrentItem(2);
//			}
//		}
//	}

}
