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
package kr.ssulsday.comm.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class EgovTagPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public EgovTagPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	*/
	public void initVariables() {

		
		firstPageLabel = "<li><a id=\"PageText_start_link\" href=\"#\" onclick=\"{0}({1}); return false;\"> << </a></li>&#160;";
		previousPageLabel = "<li><a id=\"PageText_prev_btn\" href=\"#\" onclick=\"{0}({1}); return false;\"> < </a></li>&#160;";
		//currentPageLabel = "<strong class='focus'>{0}</strong> &#160;";
		currentPageLabel = "<li><span><strong>{0}</strong></span></li>&#160;";
		otherPageLabel = "<li><a  href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>&#160;";
		nextPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\"> > </a></li>&#160;";
		lastPageLabel = "<li><a id=\"PageText_end_link\" href=\"#\" onclick=\"{0}({1}); return false;\"> >> </a></li>&#160;";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
