package com.hzdracom.core.views;

import com.hzdracom.core.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FoodPopupWindow extends PopupWindow implements OnItemClickListener {

	private static final String[] arrayBrand = {
		"全部品牌",
		"虹光", 
		"祖名",
		"......" };
	private static final String[] arrayType = { 
		"全部分类", 
		"禽畜", 
		"豆制品", 
		"......" };
	private static final String[] arraySequence = { 
		"销量最高",
		"评分最高", 
		"价格最高",
		"价格最低" };
	private static final String[] arrayCity = { 
		"全城",
		"上城区", 
		"下城区",
		"......" };
	private static final String[] arrayShop = { 
		"全部商户", 
		"美食",
		"娱乐",
		"......" };
	private Context context;
	SortAdapter mAdapter;
	ListView vListView;
	private String initName;
	private int type;
	private int temp;

	public FoodPopupWindow(Context context, int temp) {
		super(LayoutInflater.from(context).inflate(R.layout.common_views_shop_sort, null));
		this.context = context;
		this.temp = temp;

		findViews();
		setListeners();

		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);

		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable(context.getResources(),(Bitmap) null));

	}

	private void setListeners() {
		vListView.setOnItemClickListener(this);
	}

	private void findViews() {
		View view = getContentView();
		view.findViewById(R.id.shop_sort_bottom).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
		vListView = (ListView) view.findViewById(R.id.shop_sort_lv);
		mAdapter = new SortAdapter();
		vListView.setAdapter(mAdapter);
	}

	public void initData(int type, String name) {
		switch (temp) {
		case 1:
			initName = name;
			this.type = type;
			if (type == 0) {
				mAdapter.setData(arrayBrand);

			} else if (type == 1) {
				mAdapter.setData(arrayType);
			} else if (type == 2) {
				mAdapter.setData(arraySequence);
			}
			break;
		case 2:
			initName = name;
			this.type = type;
			if (type == 0) {
				mAdapter.setData(arrayCity);

			} else if (type == 1) {
				mAdapter.setData(arrayShop);
			} else if (type == 2) {
				mAdapter.setData(arraySequence);
			}
			break;
		default:
			break;
		}
	}

	private Listener listener;

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public interface Listener {
		void onSelect(String name, int type, int position);
	}

	private class SortAdapter extends BaseAdapter {
		private String[] mData = new String[] {};

		public void setData(String[] data) {
			mData = data;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mData.length;
		}

		@Override
		public Object getItem(int position) {
			return mData[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String sortName = (String) getItem(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.common_views_shop_sort_item, null);
			}
			TextView tvName = (TextView) convertView.findViewById(R.id.shop_sort_item_name);
			View vDivider = convertView.findViewById(R.id.shop_sort_item_divider);
			if (sortName.equals(initName)) {
				tvName.setTextColor(Color.rgb(235, 65, 62));
				vDivider.setBackgroundColor(Color.rgb(235, 65, 62));
			} else {
				tvName.setTextColor(Color.rgb(50, 50, 50));
				vDivider.setBackgroundColor(Color.rgb(217, 217, 217));
			}
			tvName.setText(sortName);
			return convertView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.dismiss();
		listener.onSelect(mAdapter.getItem(position).toString(), type, position);
	}

}
