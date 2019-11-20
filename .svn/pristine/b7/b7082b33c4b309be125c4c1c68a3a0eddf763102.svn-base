package kr.ssulsday.cms.service;

import kr.ssulsday.cms.vo.UserInfoVO;

import java.sql.Date;

import kr.ssulsday.cms.vo.LoginVO;

public interface LoginService {

	UserInfoVO selectUserLogin(LoginVO vo) throws Exception;
	public void keepLogin(String user_id, String sessionId, Date next);
	public UserInfoVO checkUserWithSessionKey(String sessionId);
}
