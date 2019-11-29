package kr.ssulsday.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.ComStr;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.ActivityInfoService;
import kr.ssulsday.cms.service.HashtagInfoService;
import kr.ssulsday.cms.service.UserInfoService;
import kr.ssulsday.cms.vo.ActivityInfoVO;
import kr.ssulsday.cms.vo.CardInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;
import kr.ssulsday.cms.vo.UserInfoVO;

@Controller
@RequestMapping(value="/cms/activity")
public class ActivityInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityInfoController.class);
	
	@Resource
	private ActivityInfoService activityinfoService;
	
	@Resource
	private HashtagInfoService HashtagInfoService;
	
	@Autowired
	private	MessageSource messageSource;
	
	/**
	 * 처리 내용 : 내가 쓴글 리스트
	 */
	@RequestMapping(value="/mycontentlist.do",method = RequestMethod.GET)
	public @ResponseBody List<?> 
	myContentListForm(@RequestBody SearchPageVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		PagingListVO	listVO = activityinfoService.selectcontentListPage(searchVO);
		
		List<CardInfoVO> cardList = (List<CardInfoVO>) listVO.getItems();
		for (CardInfoVO vo:cardList) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO:hashList) {
				hashtagList.add(hashtagVO.getHashtag());
			}
			vo.setHashtags(hashtagList);
		}

		return listVO.getItems();
		
	}
	/**
	 * 처리 내용 : 내 댓글이 존재하는 글의 리스트
	 */
	@RequestMapping(value = "/mycommentlist.do", method = RequestMethod.GET)
	public @ResponseBody List<?>  
	myCommentListForm(@RequestBody SearchPageVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		PagingListVO	listVO = activityinfoService.selectcommentListPage(searchVO);
		
		List<CardInfoVO> cardList = (List<CardInfoVO>) listVO.getItems();
		for (CardInfoVO vo:cardList) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO:hashList) {
				hashtagList.add(hashtagVO.getHashtag());
			}
			vo.setHashtags(hashtagList);
		}
		
		model.addAttribute(PagingListVO.ATTR_RESULT_LIST, 	listVO.getItems());
		model.addAttribute(SearchPageVO.ATTR_SEARCH_PAGE, 	listVO.getSearchPage());
		model.addAttribute(SearchPageVO.ATTR_PAGING_INFO, 	listVO.getPagingInfo());

		return listVO.getItems();
		
	}
	/**
	 * 처리 내용 : 내 댓글이 존재하는 글의 리스트
	 */
	@RequestMapping(value = "/mylikelist.do", method = RequestMethod.GET)
	public @ResponseBody List<?> 
	myLikeListForm(@RequestBody SearchPageVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		PagingListVO	listVO = activityinfoService.selectlikeListPage(searchVO);
		
		List<CardInfoVO> cardList = (List<CardInfoVO>) listVO.getItems();
		for (CardInfoVO vo:cardList) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO:hashList) {
				hashtagList.add(hashtagVO.getHashtag());
			}
			vo.setHashtags(hashtagList);
		}
		
		model.addAttribute(PagingListVO.ATTR_RESULT_LIST, 	listVO.getItems());
		model.addAttribute(SearchPageVO.ATTR_SEARCH_PAGE, 	listVO.getSearchPage());
		model.addAttribute(SearchPageVO.ATTR_PAGING_INFO, 	listVO.getPagingInfo());

		return listVO.getItems();
		
	}
	@RequestMapping(value="/mycount.do",method = RequestMethod.GET)
	public @ResponseBody ActivityInfoVO
	myListCount(@RequestBody SearchPageVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		ActivityInfoVO activityVO = new ActivityInfoVO();
		activityVO.setmyLikecount(activityinfoService.selectlikeListCount(searchVO));
		activityVO.setmyCommentcount(activityinfoService.selectcommentListCount(searchVO));
		activityVO.setmyPostcount(activityinfoService.selectcontentListCount(searchVO));
		return activityVO;
	}
}
