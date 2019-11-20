/*
 * Copyright 2008-2009 the original author or authors.
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
 */
package kr.cubex.comm.vo;

import org.springframework.context.MessageSource;

public class ResultVO extends BaseResVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 데이터카운트 */
    private int		rowCount 	= 0;
    private String	resKey;
    private String	resType;
    private String	extraData;
    private int		sent_count	= 0;
    
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getExtraData() {
		return extraData;
	}
	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	public int getSent_count() {
		return sent_count;
	}
	public void setSent_count(int sent_count) {
		this.sent_count = sent_count;
	}
	//
	public static ResultVO create(int nRetCode) {
		ResultVO	vo = new ResultVO();
		vo.setRetCode(nRetCode);
		return vo;
	}
	public static ResultVO create(int nRetCode, MessageSource messageSource) {
		ResultVO	vo = new ResultVO();
		vo.setRetCode(nRetCode, messageSource);
		return vo;
	}

}
