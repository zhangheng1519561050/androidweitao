package com.example.adapters;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidweitao.R;
import com.example.frament.Fragment4.shoppingproductImg;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class shoppingcartAdapter extends BaseAdapter {
	private JSONArray jsonarr;
	private LayoutInflater inflater;
	private Map<String, shoppingproductImg> bitmaps;

	public shoppingcartAdapter() {

	}

	public shoppingcartAdapter(Context context,JSONArray jsonarr) {
		super();
		this.jsonarr = jsonarr;
		this.inflater = LayoutInflater.from(context);
	}

	public shoppingcartAdapter(Context context, JSONArray jsonarr,
			Map<String, shoppingproductImg> bitmap) {
		inflater = LayoutInflater.from(context);
		bitmaps = bitmap ;
		this.jsonarr = jsonarr;
	}

	@Override
	public int getCount() {
		return jsonarr.length();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View myview = inflater.inflate(R.layout.shoppingcartuint, null);
		//找控件
		ImageView Pic_shopping_product = (ImageView) myview
				.findViewById(R.id.Pic_shopping_product);
		TextView shopping_productname = (TextView) myview
				.findViewById(R.id.shopping_productname);
		TextView shopping_productSales = (TextView) myview
				.findViewById(R.id.shopping_productSales);
		TextView shopping_productPrice = (TextView) myview
				.findViewById(R.id.shopping_productPrice);
		EditText shopping_productnum = (EditText) myview
				.findViewById(R.id.shopping_productnum);
		ImageView add_shopping_btn = (ImageView) myview
				.findViewById(R.id.add_shopping_btn);
		ImageView reduce_shopping_btn = (ImageView) myview
				.findViewById(R.id.reduce_shopping_btn);
		try {
			JSONObject object = jsonarr.getJSONObject(position);
			//Bitmap bm = bitmaps.get(1).img;
			//取出id对应的图片
			//显示商品对应信息
		//	Pic_shopping_product.setImageBitmap(bm);
			shopping_productname.setText(object.getString("productName"));
			shopping_productSales.setText(String.valueOf(object.getInt("productprice")));
			shopping_productnum.setText(String.valueOf(object.getInt("oneproductnum")));
			shopping_productPrice.setText(String.valueOf(object.getInt("oneallprice")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return myview;
	}

}
