package com.example.adapters;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.androidweitao.R;
import com.example.frament.Fragment3.ClassificationImg;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassificationAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private JSONArray jsonarr;
	private Map<String, ClassificationImg> bitmaps;

	// 建立构造函数
	public ClassificationAdapter() {
	}

	public ClassificationAdapter(Context context, JSONArray jsonarr,
		Map<String, ClassificationImg> bitmap) {
		inflater = LayoutInflater.from(context);
		bitmaps = bitmap;
		this.jsonarr = jsonarr;
	}
	public ClassificationAdapter(Context context, JSONArray jsonarr){
		this.jsonarr = jsonarr;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return jsonarr.length();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View myview = inflater.inflate(R.layout.classificationunit, null);
		// 找控件
		ImageView Pic_classification = (ImageView) myview
				.findViewById(R.id.Pic_classification);
		TextView TextBig_classific = (TextView) myview
				.findViewById(R.id.TextBig_classific);
		TextView TextSmall_classific = (TextView) myview
				.findViewById(R.id.TextSmall_classific);
		// 显示数据
		try {
			//定义JSONObject
			JSONObject object = jsonarr.getJSONObject(position);
			Bitmap bm = bitmaps.get(object.getString("producttypeId")).img;
			//取出id对应的图片
			//显示商品对应信息
			Pic_classification.setImageBitmap(bm);
			TextBig_classific
					.setText(object.getString("producttypeParentName"));
			TextSmall_classific.setText(object
					.getString("producttypeChildName"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myview;
	}

}
