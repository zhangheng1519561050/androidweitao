package com.example.adapters;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.androidweitao.R;
import com.example.androidweitao.productActivity.productImg;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class productAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private JSONArray jsonarr;
	private Map<String, productImg> bitmaps;
	private Handler handler;

	public productAdapter() {

	}

	public productAdapter(Context context,  JSONArray jsonarr,
			Map<String, productImg> bitmap) {
		inflater = LayoutInflater.from(context);
		bitmaps = bitmap ;
		this.jsonarr=jsonarr;
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

		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {

		View myview = inflater.inflate(R.layout.productuint, null);
		
		// 找控件
		ImageView Pic_product = (ImageView) myview.findViewById(R.id.Pic_product);
		TextView productname = (TextView) myview.findViewById(R.id.productname);
		TextView productPrice = (TextView) myview.findViewById(R.id.productPrice);
		TextView productDate = (TextView) myview.findViewById(R.id.productDate);
		//Button addshoppingCart=(Button) myview.findViewById(R.id.addshoppingCart);
		
	
		// 显示数据
		try {
			//定义JSONObject
			JSONObject object = jsonarr.getJSONObject(position);
			Bitmap bm = bitmaps.get(object.getString("productId")).img;
			//取出id对应的图片
			//显示商品对应信息
			productname.setText(object.getString("productName"));
			productPrice.setText(object.getString("productprice"));
			productDate.setText(object.getString("posttime"));
			Pic_product.setImageBitmap(bm);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	/*addshoppingCart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						putid(position);
					}

					private void putid(int position) {
						// TODO Auto-generated method stub
						
						
					}
				});
				
			}
		});*/
	
		return myview;
	}

}
