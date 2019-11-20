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

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;

import kr.cubex.utils.ComDate;


public class ResultListVO extends BaseResVO {
	
	private static final long serialVersionUID = 1L;
    
	/** 데이터카운트 */
	private	List<?>		items;
	private	String		curr_date;
    
	public ResultListVO() {
		curr_date	= ComDate.makeDispDateTimeString();
	}
	
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {

		this.items = items;

		if (this.items == null)
			this.items = new ArrayList<String>();
	}
	public void setList(List<?> items) {
		setItems(items);
	}

	public String getCurr_date() {
		return curr_date;
	}
	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}

	//
	public static ResultListVO create(int nRetCode) {
		ResultListVO	vo = new ResultListVO();
		vo.setRetCode(nRetCode);
		return vo;
	}
	public static ResultListVO create(int nRetCode, MessageSource messageSource) {
		ResultListVO	vo = new ResultListVO();
		vo.setRetCode(nRetCode, messageSource);
		return vo;
	}
}
