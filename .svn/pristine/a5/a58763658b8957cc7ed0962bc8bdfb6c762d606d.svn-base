/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.ssulsday.cms.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.ssulsday.cms.mapper.LoginMapper;
import kr.ssulsday.cms.service.LoginService;
import kr.ssulsday.cms.vo.UserInfoVO;
import kr.ssulsday.cms.vo.LoginVO;


@Service
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	
    SqlSession sqlSession;

	@Resource
	private  LoginMapper loginmapper;

	@Override
	public UserInfoVO selectUserLogin(LoginVO vo) {
		return loginmapper.selectUserLogin(vo);
	}
	
	public void keepLogin(String user_id,String sessionId, Date next) {
		 Map<String, Object> map =new HashMap<String,Object>();
	        map.put("userId", user_id);
	        map.put("sessionId", sessionId);
	        map.put("next", next);
	        loginmapper.keepLogin(user_id, sessionId, next);
	        
	}

	@Override
	public UserInfoVO checkUserWithSessionKey(String sessionId) {
		// TODO Auto-generated method stub
		return loginmapper.checkUserWithSessionKey(sessionId);
	}
}
