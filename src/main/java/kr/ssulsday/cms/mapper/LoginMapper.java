package kr.ssulsday.cms.mapper;

import java.sql.Date;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.ssulsday.cms.vo.UserInfoVO;
import kr.ssulsday.cms.vo.LoginVO;

@Mapper
public interface LoginMapper {

	UserInfoVO selectUserLogin(LoginVO vo);
	public void keepLogin(String user_id, String sessionId, Date next);
	public UserInfoVO checkUserWithSessionKey(String sessionId);

}
