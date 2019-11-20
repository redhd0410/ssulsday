package kr.ssulsday.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.ssulsday.cms.mapper.HashtagInfoMapper;
import kr.ssulsday.cms.mapper.PostInfoMapper;
import kr.ssulsday.cms.service.HashtagInfoService;
import kr.ssulsday.cms.vo.CommentInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;

@Service
public class HashtagInfoServiceImpl implements HashtagInfoService {

	/*private static final Logger logger = LoggerFactory.getLogger(HashtagInfoServiceImpl.class);
*/
	@Resource
	private HashtagInfoMapper hashtagInfoMapper;
	
	@Override
	public void insertData(HashtagInfoVO vo) throws Exception {
		hashtagInfoMapper.insertData(vo);
	}
	
	@Override
	public void insertUpdateData(HashtagInfoVO vo) throws Exception {
		hashtagInfoMapper.insertUpdateData(vo);
	}

	@Override
	public void updateData(HashtagInfoVO vo) throws Exception {
		hashtagInfoMapper.updateData(vo);
	}

	public List<HashtagInfoVO> selectHashtagListData(int post_id){
		return hashtagInfoMapper.selectHashtagListData(post_id);
	}

	public int selectHashtagListCount(int post_id) {
		return hashtagInfoMapper.selectHashtagListCount(post_id);
	}

	@Override
	public void deleteData(int post_id) throws Exception {
		hashtagInfoMapper.deleteData(post_id);
	}
	
	//해시태그 검색관련
	public List<?> selectListData(SearchPageVO vo) throws Exception {
		return hashtagInfoMapper.selectListData(vo);
	}

	public int selectListCount(SearchPageVO vo) {
		return hashtagInfoMapper.selectListCount(vo);
	}
	//
	
	public PagingListVO selectListPage(SearchPageVO vo) throws Exception {
		PagingListVO	lstPageData	= new PagingListVO(vo);
		
		List<?> list	= selectListData(vo);
		int totCnt		= selectListCount(vo);
		
		lstPageData.setTotalCount(totCnt);
		lstPageData.setItems(list);
		
		return lstPageData;
	}
}
