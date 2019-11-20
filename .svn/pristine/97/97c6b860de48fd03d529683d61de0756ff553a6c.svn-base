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
import kr.ssulsday.cms.mapper.CommentInfoMapper;
import kr.ssulsday.cms.service.CommentInfoService;
import kr.ssulsday.cms.vo.CommentInfoVO;



@Service
public class CommentInfoServiceImpl extends EgovAbstractServiceImpl implements CommentInfoService {

	// private static final Logger logger = LoggerFactory.getLogger(AdminInfoServiceImpl1.class);

	@Resource
	private CommentInfoMapper commentInfoMapper;

	@Override
	public void insertData(CommentInfoVO vo) throws Exception {
		commentInfoMapper.insertData(vo);
	}

	@Override
	public int updateData(CommentInfoVO vo) throws Exception {
		return commentInfoMapper.updateData(vo);
	}

	@Override
	public int deleteData(CommentInfoVO vo) throws Exception {
		return commentInfoMapper.deleteData(vo);
	}

	@Override
	public CommentInfoVO selectData(CommentInfoVO vo) throws Exception {
		return commentInfoMapper.selectData(vo);
	}

	@Override
	public List<?> selectListData(SearchPageVO vo) throws Exception {
		return commentInfoMapper.selectListData(vo);
	}

	@Override
	public int selectListCount(SearchPageVO vo) {
		return commentInfoMapper.selectListCount(vo);
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
