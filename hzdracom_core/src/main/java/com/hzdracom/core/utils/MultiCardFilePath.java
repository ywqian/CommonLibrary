/*
 * ========================================================
 * ClassName:
 * Description:
 * Copyright (C) 2012 中国电信翼聊运营支撑中心
 * ========================================================
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *===================================================================*
 * Revision History
 *
 * Modification                    Tracking
 * Date         Author          Number       Description of changes
 *____________________________________________________________________
 * 
 */
package com.hzdracom.core.utils;

/**
 * 多存储卡文件路径信息
 * @author cjx
 *
 */
public class MultiCardFilePath {
	/**
	 * 返回OK
	 */
	public static final int RET_OK = 0;
	
	/**
	 * 返回内存不多预警
	 */
	public static final int RET_LIMIT_SPACE_WARNNING = 1;
	
	/**
	 * 文件完整路径
	 */
	private String filePath;
	
	/**
	 * 返回码
	 */
	private int code;
	
	
	/**
	 * 获取文件路径
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * 设置文件路径
	 * @param mFilePath
	 */
	public void setFilePath(String mFilePath) {
		this.filePath = mFilePath;
	}
	
	/**
	 * 获取返回码
	 * @return
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * 设置返回码
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
