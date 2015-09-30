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
 * 存储空间不足，此时写入操作失败
 * @author cjx
 *
 */
public class LimitSpaceUnwriteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -360543214883668013L;

	public LimitSpaceUnwriteException() {
        super("存储空间不足，无法写入");
    }
}
