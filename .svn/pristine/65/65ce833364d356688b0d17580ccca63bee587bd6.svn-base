package kr.ssulsday.cms.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.CommentInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;

@Mapper
public interface HashtagInfoMapper {

	void insertData(HashtagInfoVO vo);

	void updateData(HashtagInfoVO vo);

	void deleteData(int post_id);
	
	void insertUpdateData(HashtagInfoVO vo);

	List<HashtagInfoVO> selectHashtagListData(int post_id);

	int selectHashtagListCount(int post_id);

	List<?> selectListData(SearchPageVO vo);

	int selectListCount(SearchPageVO vo);
	
	PagingListVO selectListPage(SearchPageVO vo);
	

	
}
