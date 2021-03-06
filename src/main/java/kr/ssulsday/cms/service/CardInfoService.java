package kr.ssulsday.cms.service;

import java.util.List;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseService;
import kr.ssulsday.cms.vo.CardInfoVO;

public interface CardInfoService extends CmBaseService<CardInfoVO, SearchPageVO>{

	
	
	/**
     * 데이터 리스트 및 페이징정보 반환
     */
	PagingListVO selectListPage(SearchPageVO vo) throws Exception;

	int updateViewCount(CardInfoVO cardvo) throws Exception;
	
	int updateCount(int post_id, int count, String flag) throws Exception;
	
	List<?> selectListDataByRange(double latitude, double longitude) throws Exception;

	CardInfoVO selectData(int post_id);
}
