package kr.ssulsday.cms.service;

import java.util.List;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseService;
import kr.ssulsday.cms.vo.CommentInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;

public interface HashtagInfoService {
	
	public void insertData(HashtagInfoVO vo) throws Exception;
	public void insertUpdateData(HashtagInfoVO vo) throws Exception;
	public void deleteData(int post_id) throws Exception;
	public void updateData(HashtagInfoVO vo) throws Exception;
	public List<HashtagInfoVO> selectHashtagListData(int post_id) throws Exception;
	public int selectHashtagListCount(int post_id) throws Exception;
	//해시태그 검색관련
	public List<?> selectListData(SearchPageVO vo) throws Exception;
	public int selectListCount(SearchPageVO vo) throws Exception;
	//
	 PagingListVO selectListPage(SearchPageVO vo) throws Exception;
	
	
}
