package com.hzdracom.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzdracom.core.R;
import com.hzdracom.core.utils.Utils;

/**
 * 底部输入框
 * 
 */
public class CommentView
        extends
        LinearLayout
        implements
        OnClickListener
{
	private ImageView            ivEmoji;
	private EditText             etContent;
	private Button               submitBtn;
	private Context              mContext;
	private String               btnString = "评论";
	
	                                                
	public CommentView(Context context) {
		super(context);
		initView(context);
	}
	
	public CommentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	@SuppressLint("NewApi")
	public CommentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	
	private void initView(Context context) {
		// TODO Auto-generated method stub
		mContext = context;
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		RelativeLayout containerView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.common_views_comment_view, null);
		ivEmoji = (ImageView) containerView.findViewById(R.id.comment_view_emoji);
		etContent = (EditText) containerView.findViewById(R.id.comment_view_content);
		submitBtn = (Button) containerView.findViewById(R.id.comment_view_commit);
		
		submitBtn.setText(btnString);
		setDefaultHint();
		
		etContent.addTextChangedListener(new TextChanged());
		etContent.setOnFocusChangeListener(new FocusChange());
		ivEmoji.setOnClickListener(this);
		submitBtn.setOnClickListener(this);
		addView(containerView, lp);
	}

	/** 设置自定义提示语 */
	public void setHintString(String nickName) {
		if (null == etContent || Utils.isEmpty(nickName)) return;
		etContent.setHint(String.format("回复%s:", nickName));
	}
	
	/** 设置默认的提示语 */
	public void setDefaultHint() {
		if (null == etContent) return;
		etContent.setHint("发表一下您的看法吧~限200字");
	}
	
	/** 设置右侧按钮显示的文字 */
	public void setButtonString(String str) {
		if (null == etContent || Utils.isEmpty(str)) return;
		btnString = str;
		etContent.setText(str);
	}
	
	/** 隐藏输入法 */
	public void hideInputMethod() {
		if (null == etContent || null == mContext) return;
		Utils.hideInputMethodManager(mContext, etContent);
	}
	
	/** 显示输入法 */
	public void showInputMethod() {
		if (null == etContent || null == mContext) return;
		Utils.showInputMethodManager(mContext);
		etContent.setFocusable(true);
		etContent.setFocusableInTouchMode(true);
		etContent.requestFocus();
	}
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case 0:
				commit();
				break;
		}
	}
	
	/** 提交 */
	private void commit() {
		
	}
	
	
	private class TextChanged
	        implements
	        TextWatcher
	{
		
		@Override
		public void afterTextChanged(Editable arg0) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		}
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			enabledBtn(Utils.isNotEmpty(arg0.toString()));
		}
	}
	
	private void enabledBtn(boolean value) {
		if (null == submitBtn || null == mContext) return;
		submitBtn.setTextColor(mContext.getResources().getColor(value ? R.color.comment_tv_color_enable : R.color.comment_tv_color));
		submitBtn.setEnabled(value);
	}
	
	private class FocusChange
	        implements
	        OnFocusChangeListener
	{
		@Override
		public void onFocusChange(View arg0, boolean arg1) {
			if (null == submitBtn || null == mContext) return;
			if (arg1)
			{
				//				ivEmoji.setVisibility(View.VISIBLE);
				submitBtn.setVisibility(View.VISIBLE);
			}
			else
			{
				//				ivEmoji.setVisibility(View.GONE);
				submitBtn.setVisibility(View.GONE);
				//隐藏输入法
				Utils.hideInputMethodManager(mContext, arg0);
			}
		}
	}
	
	
	
//	private boolean checkNull(Object... params) {
//		for (Object object : params)
//		{
//			if (null == object || Utils.isEmpty(object.toString()))
//			{
//				//TODO
//				Utils.showToast(mContext, "必填参数为空");
//				enabledBtn(true);
//				return false;
//			}
//		}
//		return true;
//	}
}
