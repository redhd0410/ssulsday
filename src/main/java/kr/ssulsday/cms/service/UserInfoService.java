package kr.ssulsday.cms.service;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseService;
import kr.ssulsday.cms.vo.UserInfoVO;

public interface UserInfoService extends CmBaseService<UserInfoVO, SearchPageVO>{

	
	
	/**
     * 데이터 리스트 및 페이징정보 반환
     */
	PagingListVO selectListPage(SearchPageVO vo) throws Exception;
	UserInfoVO selectData(String user_id) throws Exception;
}
