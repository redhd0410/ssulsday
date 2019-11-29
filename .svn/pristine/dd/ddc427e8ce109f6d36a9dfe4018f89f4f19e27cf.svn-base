package kr.ssulsday.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.LikeInfoVO;

@Mapper
public interface LikeInfoMapper extends CmBaseMapper<LikeInfoVO, SearchPageVO> {

	List<?> selectListData(SearchPageVO schVO);

	int selectListCount(SearchPageVO schVO);

	int selectDataCount(LikeInfoVO likevo);

	int selectUserLiked(SearchPageVO vo);
	
}
