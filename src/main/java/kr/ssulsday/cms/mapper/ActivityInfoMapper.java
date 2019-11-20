package kr.ssulsday.cms.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.UserInfoVO;

@Mapper
public interface ActivityInfoMapper {
	// 내가 쓴글
	List<?> selectcontentListData(SearchPageVO schVO);

	int selectcontentListCount(SearchPageVO schVO);

	// 댓글 단글
	List<?> selectcommentListData(SearchPageVO schVO);

	int selectcommentListCount(SearchPageVO schVO);

	// 좋아요 누른 글
	List<?> selectlikeListData(SearchPageVO schVO);

	int selectlikeListCount(SearchPageVO schVO);
}
