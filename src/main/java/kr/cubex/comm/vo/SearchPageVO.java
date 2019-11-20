package kr.cubex.comm.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class SearchPageVO {
	//
	public static final String	ATTR_SEARCH_PAGE		= "searchPage";
	public static final String	ATTR_PAGING_INFO		= "pagingInfo";

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색조건1 */
    private String searchCondition1 = "";
    
    /** 검색조건2 */
    private int searchCondition2 = 0;
    
    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 - pageNo */
    private int pageIndex = 1;

    /** firstIndex Default = 0 */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** 페이지당 레코드 갯수 - recordCountPerPage */
    private int recordCountPerPage = 10;

	/** 페이지갯수 - input 파마미터 */
	private int pageUnit = 10;

    /** 페이지사이즈 - input 파마미터 */
    private int pageSize = 10;
    
    private int post_id = 0;

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    
    public String getSearchCondition1() {
		return searchCondition1;
	}

	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}

	public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	@Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
	public String getSEARCH_TYPE() {
        return searchCondition;
    }
	public String getSEARCH_WORD() {
        return searchKeyword;
    }

	public int getSearchCondition2() {
		return searchCondition2;
	}

	public void setSearchCondition2(int searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
}
