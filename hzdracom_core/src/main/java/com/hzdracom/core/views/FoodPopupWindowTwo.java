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
 * @Description：二级列表弹窗
 * @Date：2015年7月21日下午4:19:31
 */
public class FoodPopupWindowTwo
        extends
        PopupWindow
{
	
	private List<String> dataListLeft         = new ArrayList<String>(); //该数据需要在 fragment 中自己去解析。弹窗只负责显示
	private List<String> dataListRight        = new ArrayList<String>(); //该数据需要在 fragment 中自己去解析。弹窗只负责显示
	private Context      context;
	private LeftAdapter  leftAdapter;                                   //左侧
	private RightAdapter rightAdapter;                                  //右侧
	private ListView     leftLv;                                        //
	private ListView     rightLv;                                       //
	private int          curPositionLeft      = 0;                      //左侧popupWindow 当前选中的位置 
	private int          curPositionRight     = 0;                      //右侧popupWindow 当前选中的位置   
	private String       curNameRight         = "";
	private boolean      leftPositionIsChange = true;
	private int          curPageType;                                   //fragment页面类型,页面的显示需要根据此字段进行判断进行解析
	private int          popupWindowPosition;                           //popupWindow 在页面的位置，比如左中右
	                                                                     //	private final int    PAGE_TAG_1     = 1;                      //左边弹窗
	                                                                     //	private final int    PAGE_TAG_TAG_2 = 2;                      //中间弹窗
	                                                                     //	private final int    PAGE_TAG_TAG_3 = 3;                      //右边弹窗
	                                                                     
	public FoodPopupWindowTwo(Context context, int curPageType, int popupWindowPosition) {
		super(LayoutInflater.from(context).inflate(R.layout.common_views_popup_window_two, null));
		this.context = context;
		this.curPageType = curPageType;
		this.popupWindowPosition = popupWindowPosition;
		findViews();
		addListener();
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
		
	}
	
	private void findViews() {
		View view = getContentView();
		view.findViewById(R.id.popup_window_two_bottom).setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		leftLv = (ListView) view.findViewById(R.id.popup_window_two_left_lv);
		rightLv = (ListView) view.findViewById(R.id.popup_window_two_right_lv);
		leftAdapter = new LeftAdapter();
		rightAdapter = new RightAdapter();
		leftLv.setAdapter(leftAdapter);
		rightLv.setAdapter(rightAdapter);
	}
	
	private void addListener() {
		leftLv.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listener.onSelectLeft(curPageType, popupWindowPosition, position);
				//				initNameLeft = dataListLeft.get(position).toString();//记录当前选中的内容
				leftPositionIsChange = (curPositionLeft == position) ? false : true;//记录左侧位置是否变化
				curPositionLeft = position;
				//				leftLv.getAdapter().getView(position, view, parent).setSelected(true);
				//				parent.getAdapter().getView(position, view, parent).setBackgroundColor(Color.rgb(0xf0, 0xf0, 0xf0));
				//				parent.getAdapter().getView(position, view, parent);
				leftAdapter.notifyDataSetChanged();
			}
		});
		
		rightLv.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listener.onSelectRight(curPageType, popupWindowPosition, position);
				//				initNameRight = dataListRight.get(position).toString();//记录当前选中的内容
				curPositionRight = position;//记录当前选中的位置
				curNameRight = dataListRight.get(position).toString();//记录当前选中的内容
				dismiss();
			}
		});
	}
	
	public void setDataLeft(List<String> dataListLeft) {
		this.dataListLeft = dataListLeft;
	}
	
	public void setDataRight(List<String> dataListRight) {
		this.dataListRight = dataListRight;
	}
	
	/**
	 * 刷新adapter 数据
	 * FoodPopupWindowTwo
	 * void
	 * 
	 * @param type
	 *            0 左右都刷新 1只刷新左侧adapter 2只刷新右侧adapter
	 */
	public void refrush(int type) {
		switch (type) {
			case 0:
				leftAdapter.notifyDataSetChanged();
				rightAdapter.notifyDataSetChanged();
				break;
			case 1:
				leftAdapter.notifyDataSetChanged();
				break;
			case 2:
				rightAdapter.notifyDataSetChanged();
			default:
				break;
		}
	}
	
	private FoodPopupWindowListener listener;
	
	public void setListener(FoodPopupWindowListener listener) {
		this.listener = listener;
	}
	
	private class LeftAdapter
	        extends
	        BaseAdapter
	{
		
		@Override
		public int getCount() {
			return (dataListLeft == null || dataListLeft.size() == 0) ? 0 : dataListLeft.size();
		}
		
		@Override
		public Object getItem(int position) {
			return (dataListLeft == null || dataListLeft.size() == 0) ? "" : dataListLeft.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder1;
			if (convertView == null)
			{
				holder1 = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.common_views_popup_window_item, parent, false);
				convertView.setBackgroundResource(R.drawable.popup_window_left_item_bg);
				holder1.tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
				holder1.vDivider = convertView.findViewById(R.id.popup_window_item_divider);
				convertView.setTag(holder1);
			}
			else
			{
				holder1 = (ViewHolder) convertView.getTag();
			}
			//			TextView tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
			holder1.tvName.setBackgroundResource(R.drawable.popup_window_left_item_bg);//设置左侧List 点击效果
			//	View vDivider = convertView.findViewById(R.id.shop_sort_item_divider);
			holder1.vDivider.setVisibility(View.GONE);
			String sortName = (String) getItem(position);
			//	
			if (position == curPositionLeft)
			{
				holder1.tvName.setBackgroundColor(Color.rgb(0xff, 0xff, 0xff));
				//				vDivider.setBackgroundColor(Color.rgb(235, 65, 62));
			}
			else
			{
				holder1.tvName.setBackgroundColor(Color.rgb(0xf0, 0xf0, 0xf0));
				//				vDivider.setBackgroundColor(Color.rgb(217, 217, 217));
			}
			holder1.tvName.setText(sortName);
			return convertView;
		}
	}
	
	private class RightAdapter
	        extends
	        BaseAdapter
	{
		
		@Override
		public int getCount() {
			return (dataListRight == null || dataListRight.size() == 0) ? 0 : dataListRight.size();
		}
		
		@Override
		public Object getItem(int position) {
			return (dataListRight == null || dataListRight.size() == 0) ? "" : dataListRight.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder2;
			if (convertView == null)
			{
				holder2 = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.common_views_popup_window_item, parent, false);
				holder2.tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
				holder2.vDivider = convertView.findViewById(R.id.popup_window_item_divider);
				convertView.setTag(holder2);
			}
			else
			{
				holder2 = (ViewHolder) convertView.getTag();
			}
			//			TextView tvName = (TextView) convertView.findViewById(R.id.popup_window_item_name_tv);
			//			//			tvName.setBackgroundResource(R.drawable.popup_window_left_item_bg);//设置左侧List 点击效果
			//			View vDivider = convertView.findViewById(R.id.popup_window_item_divider);
			
			String sortName = (String) getItem(position);
			//	
			if (position == curPositionRight && curNameRight.equals(sortName)/*
																			  * &&!
																			  * leftPositionIsChange
																			  */)
			{
				//				isFirstInRight=false;
				holder2.tvName.setTextColor(Color.rgb(235, 65, 62));
				holder2.vDivider.setBackgroundColor(Color.rgb(235, 65, 62));
			}
			else
			{
				holder2.tvName.setTextColor(Color.rgb(50, 50, 50));
				holder2.vDivider.setBackgroundColor(Color.rgb(217, 217, 217));
			}
			holder2.tvName.setText(sortName);
			return convertView;
		}
	}
	
	private class ViewHolder
	{
		TextView tvName;
		View     vDivider;
	}
	
}
