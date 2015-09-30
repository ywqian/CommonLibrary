package com.hzdracom.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 自定义GrideView
 * 解决ScrollView和GrideView一起使用的问题，显示的时候GrideView不能完全正确的显示
 * @author 林天训 
 * @date 2014年7月11日 下午2:57:17
 */
public class MyGridView extends GridView{

	public MyGridView(Context context) {
	    super(context);
    }

	public MyGridView(Context context, AttributeSet attrs) {
	    super(context, attrs);
    }

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
    }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
	    super.onMeasure(widthMeasureSpec, expandSpec);
	}
}