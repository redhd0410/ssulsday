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

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.ssulsday.cms.mapper.UserInfoMapper;
import kr.ssulsday.cms.service.UserInfoService;
import kr.ssulsday.cms.vo.UserInfoVO;



@Service
public class UserInfoServiceImpl extends EgovAbstractServiceImpl implements UserInfoService {

	// private static final Logger logger = LoggerFactory.getLogger(AdminInfoServiceImpl1.class);

	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public void insertData(UserInfoVO vo) throws Exception {
		userInfoMapper.insertData(vo);
	}

	@Override
	public int updateData(UserInfoVO vo) throws Exception {
		return userInfoMapper.updateData(vo);
	}

	@Override
	public int deleteData(UserInfoVO vo) throws Exception {
		return userInfoMapper.deleteData(vo);
	}

	@Override
	public UserInfoVO selectData(UserInfoVO vo) throws Exception {
		return userInfoMapper.selectData(vo);
	}
	
	public UserInfoVO selectData(String user_id) throws Exception {
		return userInfoMapper.selectData(user_id);
	}
	@Override
	public List<?> selectListData(SearchPageVO vo) throws Exception {
		return userInfoMapper.selectListData(vo);
	}

	@Override
	public int selectListCount(SearchPageVO vo) {
		return userInfoMapper.selectListCount(vo);
	}

	@Override
	public PagingListVO selectListPage(SearchPageVO vo) throws Exception {
		PagingListVO	lstPageData	= new PagingListVO(vo);
		
		List<?> list	= selectListData(vo);
		int totCnt		= selectListCount(vo);
		
		lstPageData.setTotalCount(totCnt);
		lstPageData.setItems(list);
		
		return lstPageData;
	}
}
