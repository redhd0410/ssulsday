package kr.ssulsday.cms.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.comm.web.CmBaseMapper;
import kr.ssulsday.cms.vo.UserInfoVO;

@Mapper
public interface UserInfoMapper extends CmBaseMapper<UserInfoVO, SearchPageVO> {

	List<?> selectListData(SearchPageVO schVO);

	int selectListCount(SearchPageVO schVO);

	UserInfoVO selectData(String user_id);

}
