package kr.ssulsday.cms.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.CommentInfoVO;

@Mapper
public interface CommentInfoMapper extends CmBaseMapper<CommentInfoVO, SearchPageVO> {

	List<?> selectListData(SearchPageVO schVO);

	int selectListCount(SearchPageVO schVO);
}
