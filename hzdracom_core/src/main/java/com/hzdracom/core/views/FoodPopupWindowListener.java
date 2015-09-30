package com.hzdracom.core.views;

/**
 * @CommunityO2O 社区020
 * @Title：FoodPopupWindowListener.java
 * @author zhangnannan
 * @Description： 用一句话描述该页面
 * @Date：2015年7月22日上午10:35:22
 */

public interface FoodPopupWindowListener
{
	/**
	 * 单级列表弹窗点击事件
	 * Listener
	 * @param dataPosition
	 *            回传的数据位置
	 * @param curPageType
	 *            回传给哪个 fragment
	 * @param popupWindowPosition
	 *            fragment页面哪个弹窗 比如左红右
	 */
	//		void onSelect(Object data, int curPageType, int tPopupWindowPosition);
	void onSelectSingle(int curPageType, int popupWindowPosition, int dataPosition);
	
	/**
	 * 二级列表弹窗左侧list点击事件
	 * FoodPopupWindowListener
	 * @param dataPosition
	 *            回传的数据位置
	 * @param curPageType
	 *            回传给哪个 fragment
	 * @param tPopupWindowPosition
	 *            fragment页面哪个弹窗 比如左红右
	 */
	void onSelectLeft(int curPageType, int tPopupWindowPosition, int dataPosition);
	
	/**
	 * 二级列表弹窗右侧list点击事件
	 * FoodPopupWindowListener
	 * @param dataPosition
	 *            回传的数据位置
	 * @param curPageType
	 *            回传给哪个 fragment
	 * @param tPopupWindowPosition
	 *            fragment页面哪个弹窗 比如左红右
	 */
	void onSelectRight(int curPageType, int tPopupWindowPosition, int dataPosition);
	
}
