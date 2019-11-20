package kr.ssulsday.cms.service;

import java.util.List;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseService;
import kr.ssulsday.cms.vo.UserInfoVO;

public interface ActivityInfoService {

	// 내가 쓴 글
	List<?> selectcontentListData(SearchPageVO schVO) throws Exception;

	int selectcontentListCount(SearchPageVO schVO);

	PagingListVO selectcontentListPage(SearchPageVO vo) throws Exception;

	// 댓글 단글
	List<?> selectcommentListData(SearchPageVO schVO) throws Exception;

	int selectcommentListCount(SearchPageVO schVO);

	PagingListVO selectcommentListPage(SearchPageVO vo) throws Exception;

	// 좋아요 한 글
	List<?> selectlikeListData(SearchPageVO schVO) throws Exception;

	int selectlikeListCount(SearchPageVO schVO);

	PagingListVO selectlikeListPage(SearchPageVO vo) throws Exception;
}
