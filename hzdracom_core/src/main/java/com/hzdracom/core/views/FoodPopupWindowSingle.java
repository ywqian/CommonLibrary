package com.hzdracom.core.views;

import java.util.ArrayList;
import java.util.List;

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


/**
 * @CommunityO2O 社区020
 * @Title：FoodPopupWindow2.java
 * @author zhangnannan
 * @Description：单级弹窗
 * @Date：2015年7月21日下午4:19:31
 */
public class FoodPopupWindowSingle
        extends
        PopupWindow
        implements
        OnItemClickListener
{
	
	private List<String> dataList    = new ArrayList<String>(); //该数据需要在 fragment 中自己去解析。弹窗只负责显示
	private Context      context;
	private SortAdapter  mAdapter;                             //
	private ListView     vListView;
	//	private String       initName;                          //popupWindow 当前选中的数据   
	private int          curPageType;                          //fragment页面类型,页面的显示需要根据此字段进行判断进行解析
	private int          popupWindowPosition;                  //popupWindow 在页面的位置，比如左中右
	                                                            //	private boolean      isFirstIn=true;//用于初始化第一次进来时的默认
	private int          curPosition = 0;                       //用来记录当前位置
	                                                            
	public FoodPopupWindowSingle(Context context, int curPageType, int popupWindowPosition) {
		super(LayoutInflater.from(context).inflate(R.layout.common_views_popup_window_single, null));
		this.context = context;
		this.curPageType = curPageType;
		this.popupWindowPosition = popupWindowPosition;
		findViews();
		addListener();
		addDefaultData();
		
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
		
	}
	
	public void setData(List<String> dataList) {
		this.dataList = dataList;
	}
	
	public void refrush() {
		mAdapter.notifyDataSetChanged();
	}
	
	private void addListener() {
		vListView.setOnItemClickListener(this);
	}
	
	private void findViews() {
		View view = getContentView();
		view.findViewById(R.id.popup_window_single_bottom).setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		vListView = (ListView) view.findViewById(R.id.popup_window_single_lv);
		mAdapter = new SortAdapter();
		vListView.setAdapter(mAdapter);
	}
	
	private FoodPopupWindowListener listener;
	
	public void setListener(FoodPopupWindowListener listener) {
		this.listener = listener;
	}
	
	/**
	 * 商户默认排序
	 */
	public static final List<String> defaultStoreSort          = new ArrayList<String>()
	                                                      {
	                                                      };
	/**
	 * 商品默认排序
	 */
	public static final List<String> defaultCommoditySort = new ArrayList<String>()
	                                                      {
		                                                      
	                                                      };
	
	private void addDefaultData() {
		if (defaultStoreSort.size() > 0) defaultStoreSort.clear();
		defaultStoreSort.add("默认排序");
		defaultStoreSort.add("距离最近");
		defaultStoreSort.add("星级最高");
		
		if(defaultCommoditySort.size()>0) defaultCommoditySort.clear();
		defaultCommoditySort.add("默认排序");
		defaultCommoditySort.add("距离最近");
		defaultCommoditySort.add("价格最低");
		defaultCommoditySort.add("价格最高");
	}
	
	private class SortAdapter
	        extends
	        BaseAdapter
	{
		
		@Override
		public int getCount() {
			return (dataList == null || dataList.size() == 0) ? 0 : dataList.size();
		}
		
		@Override
		public Object getItem(int position) {
			return (dataList == null || dataList.size() == 0) ? "" : dataList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null)
			{
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.common_views_popup_window_item, parent, false);
				holder.tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
				holder.vDivider = convertView.findViewById(R.id.popup_window_item_divider);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			//			TextView tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
			//			View vDivider = convertView.findViewById(R.id.popup_window_item_divider);
			
			String sortName = (String) getItem(position);
			
			if (position == curPosition)
			{
				
				holder.tvName.setTextColor(Color.rgb(235, 65, 62));
				holder.vDivider.setBackgroundColor(Color.rgb(235, 65, 62));
			}
			else
			{
				holder.tvName.setTextColor(Color.rgb(50, 50, 50));
				holder.vDivider.setBackgroundColor(Color.rgb(217, 217, 217));
			}
			holder.tvName.setText(sortName);
			return convertView;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		this.dismiss();
		//		initName = dataList.get(position);
		curPosition = position;
		listener.onSelectSingle(curPageType, popupWindowPosition, position);
	}
	
	private class ViewHolder
	{
		TextView tvName;
		View     vDivider;
	}
}
