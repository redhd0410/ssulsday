package kr.cubex.comm.vo;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class ListPagingVO {
	//
	public static final String	ATTR_RESULT_LIST		= "resultList";

	private SearchPageVO		searchPage = null;
	private PaginationInfo		pagingInfo = new PaginationInfo();;
	
	private List<?>				items;
	
	//
	public ListPagingVO(SearchPageVO schPage) {
		this.searchPage	= schPage;
		
		doPageData();
	}
	

	public SearchPageVO getSearchPage() {
		return searchPage;
	}
	public void setSearchPage(SearchPageVO schPage) {
		this.searchPage = schPage;
	}

	public PaginationInfo getPagingInfo() {
		return pagingInfo;
	}
	public void setPagingInfo(PaginationInfo info) {
		this.pagingInfo = info;
	}

	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
	
	private void doPageData() {
		if (this.searchPage == null)
			this.searchPage	= new SearchPageVO();

		pagingInfo.setCurrentPageNo(searchPage.getPageIndex());
		pagingInfo.setRecordCountPerPage(searchPage.getRecordCountPerPage());
		pagingInfo.setPageSize(searchPage.getPageSize());
		
		searchPage.setFirstIndex(pagingInfo.getFirstRecordIndex());
		searchPage.setLastIndex(pagingInfo.getLastRecordIndex());
	}
	
	public void setTotalCount(int nTotCnt) {
		pagingInfo.setTotalRecordCount(nTotCnt);
	}
}
