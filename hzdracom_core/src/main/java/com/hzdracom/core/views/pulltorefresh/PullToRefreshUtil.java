package com.hzdracom.core.views.pulltorefresh;

import android.content.Context;
import com.hzdracom.core.R;
import com.hzdracom.core.views.pulltorefresh.base.PullToRefreshBase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 下拉、上拉工具类
 * @author 刘宾  
 * @date 2015年1月29日 下午4:20:46
 */
@SuppressWarnings ("rawtypes")
public class PullToRefreshUtil
{
	private static final SimpleDateFormat SDF    = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
	
	/** 设置上拉、下拉文案 */
    public static void initLabel(Context context,PullToRefreshBase view){
		if(null==context || null==view) return;
		//设置下拉刷新的文案
		view.getLoadingLayoutProxy(true, false).setPullLabel(getString(context,R.string.pull_to_refresh_pull_label));  
		view.getLoadingLayoutProxy(true, false).setRefreshingLabel(getString(context,R.string.pull_to_refresh_refreshing_label));  
		view.getLoadingLayoutProxy(true, false).setReleaseLabel(getString(context,R.string.pull_to_refresh_release_label)); 
		//设置上拉加载的文案
		view.getLoadingLayoutProxy(false, true).setPullLabel(getString(context,R.string.pull_to_load_pull_label));  
		view.getLoadingLayoutProxy(false, true).setRefreshingLabel(getString(context,R.string.pull_to_refresh_refreshing_label));  
		view.getLoadingLayoutProxy(false, true).setReleaseLabel(getString(context,R.string.pull_to_load_release_label)); 
	}
    /** 设置上拉文案 */
    public static void setLoadLabel(PullToRefreshBase view,String label){
    	if(null==view) return;
    	//设置上拉加载的文案
    	view.getLoadingLayoutProxy(false, true).setPullLabel(label);  
    	view.getLoadingLayoutProxy(false, true).setRefreshingLabel(label);  
    	view.getLoadingLayoutProxy(false, true).setReleaseLabel(label); 
    }
	private static String getString(Context context,int id){
		return context.getResources().getString(id);
	}
	/** 设置下拉刷新最后更新时间 */
	public static void setLastRefreshTime(PullToRefreshBase view,long time){
		if(null==view) return;
		view.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("上次更新于："+SDF.format(new Date(time)));
	}
	/** 设置上拉加载最后更新时间 */
	public static void setLastLoadTime(PullToRefreshBase view,long time){
		if(null==view) return;
		view.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上次更新于："+SDF.format(new Date(time)));
	}
}
