package kr.ssulsday.cms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseService;
import kr.ssulsday.cms.vo.LikeInfoVO;

public interface LikeInfoService extends CmBaseService<LikeInfoVO, SearchPageVO>{

	/**
     * 데이터 리스트 및 페이징정보 반환
     */
	PagingListVO selectListPage(SearchPageVO vo) throws Exception;

	int selectDataCount(LikeInfoVO likevo);
}
