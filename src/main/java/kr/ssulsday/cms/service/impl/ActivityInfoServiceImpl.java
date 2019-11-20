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
import kr.ssulsday.cms.mapper.ActivityInfoMapper;
import kr.ssulsday.cms.mapper.UserInfoMapper;
import kr.ssulsday.cms.service.ActivityInfoService;
import kr.ssulsday.cms.vo.UserInfoVO;

@Service
public class ActivityInfoServiceImpl extends EgovAbstractServiceImpl implements ActivityInfoService {

	// private static final Logger logger =
	// LoggerFactory.getLogger(AdminInfoServiceImpl1.class);

	@Resource
	private ActivityInfoMapper activityInfoMapper;

	// 내가 쓴글
	@Override
	public List<?> selectcontentListData(SearchPageVO vo) {
		return activityInfoMapper.selectcontentListData(vo);
	}

	@Override
	public int selectcontentListCount(SearchPageVO vo) {
		return activityInfoMapper.selectcontentListCount(vo);
	}

	@Override
	public PagingListVO selectcontentListPage(SearchPageVO vo) throws Exception {
		PagingListVO lstPageData = new PagingListVO(vo);

		List<?> list = selectcontentListData(vo);
		int totCnt = selectcontentListCount(vo);

		lstPageData.setTotalCount(totCnt);
		lstPageData.setItems(list);

		return lstPageData;
	}

	// 내가 댓글 단 글
	@Override
	public List<?> selectcommentListData(SearchPageVO vo) {
		return activityInfoMapper.selectcommentListData(vo);
	}

	@Override
	public int selectcommentListCount(SearchPageVO vo) {
		return activityInfoMapper.selectcommentListCount(vo);
	}

	@Override
	public PagingListVO selectcommentListPage(SearchPageVO vo) throws Exception {
		PagingListVO lstPageData = new PagingListVO(vo);

		List<?> list = selectcommentListData(vo);
		int totCnt = selectcommentListCount(vo);

		lstPageData.setTotalCount(totCnt);
		lstPageData.setItems(list);

		return lstPageData;
	}

	// 내가 좋아요한 글
	@Override
	public List<?> selectlikeListData(SearchPageVO vo) {
		return activityInfoMapper.selectlikeListData(vo);
	}

	@Override
	public int selectlikeListCount(SearchPageVO vo) {
		return activityInfoMapper.selectlikeListCount(vo);
	}

	@Override
	public PagingListVO selectlikeListPage(SearchPageVO vo) throws Exception {
		PagingListVO lstPageData = new PagingListVO(vo);

		List<?> list = selectlikeListData(vo);
		int totCnt = selectlikeListCount(vo);

		lstPageData.setTotalCount(totCnt);
		lstPageData.setItems(list);

		return lstPageData;
	}
}
