package kr.ssulsday.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.PostInfoVO;

@Mapper
public interface PostInfoMapper extends CmBaseMapper<PostInfoVO, SearchPageVO> {

	List<?> selectListData(SearchPageVO schVO);

	int selectListCount(SearchPageVO schVO);
	//

	int updateViewCount(PostInfoVO vo);

	int updateCount(@Param("post_id") int post_id, @Param("count") int count, @Param("flag") String flag);

	PostInfoVO selectData(int post_id);	
}
