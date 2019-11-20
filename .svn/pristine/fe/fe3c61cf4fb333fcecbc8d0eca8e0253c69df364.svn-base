package kr.ssulsday.cms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.PostInfoService;
import kr.ssulsday.cms.service.CardInfoService;
import kr.ssulsday.cms.service.HashtagInfoService;
import kr.ssulsday.cms.vo.LikeInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;
import kr.ssulsday.cms.vo.CardInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;

@Controller
@RequestMapping(value = "/cms/post")
public class PostInfoController {

	private static final Logger logger = LoggerFactory.getLogger(PostInfoController.class);

	@Resource
	private PostInfoService PostInfoService;
	@Resource
	private HashtagInfoService HashtagInfoService;
	@Resource
	private CardInfoService cardInfoService;
	@Autowired
	private MessageSource messageSource;
	
	private final ReentrantLock locker =new ReentrantLock();

	@RequestMapping(value = "/list.do", method=RequestMethod.GET)
	public @ResponseBody List<?>
	postListForm(@RequestParam Optional<String> searchCondition, @ModelAttribute("searchVO") SearchPageVO searchVO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		if (searchCondition.isPresent()) {
			searchVO.setSearchCondition(searchCondition.get());
		}

		PagingListVO listVO = PostInfoService.selectListPage(searchVO);
		List<PostInfoVO> postList = (List<PostInfoVO>) listVO.getItems();
		for (PostInfoVO vo:postList) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO:hashList) {
				hashtagList.add(hashtagVO.getHashtag());
			}
			vo.setHashtags(hashtagList);
		}

		return listVO.getItems();

	}

	@RequestMapping(value="/view.do/{post_id}", method=RequestMethod.GET)
	public @ResponseBody PostInfoVO 
	postViewForm(@ModelAttribute("searchVO") SearchPageVO searchVO, @PathVariable int post_id,
			ModelMap model, HttpServletRequest request) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		PostInfoVO postVO = PostInfoService.selectData(post_id);
		CardInfoVO cardvo = new CardInfoVO();
		PostInfoService.updateViewCount(postVO);
		cardvo.setPost_id(postVO.getPost_id());
		cardvo.setViewcount(postVO.getViewcount());
		cardInfoService.updateViewCount(cardvo);
		List<HashtagInfoVO> listVO = HashtagInfoService.selectHashtagListData(postVO.getPost_id());
		List<String> hashtagList = new ArrayList<String>();
		for (HashtagInfoVO vo:listVO) {
			hashtagList.add(vo.getHashtag());
		}
		postVO.setHashtags(hashtagList);
		
		logger.info(postVO.toString());

		return postVO;
	}

	@RequestMapping(value = "/reg_action.do",method = RequestMethod.POST)
	public @ResponseBody BaseResult 
	postCreateAction(@RequestBody PostInfoVO postvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO = new ResultData();
		try {
			CardInfoVO cardvo = new CardInfoVO();
			List<String> hashtagList = postvo.getHashtags();
			
			postvo.setHashtags(null);
			PostInfoService.insertData(postvo);
			
			if (hashtagList.size() == 1 && hashtagList.get(0) == "") {
			} else {
				for (String h: hashtagList) {
					HashtagInfoVO hashtag = new HashtagInfoVO();
					hashtag.setHashtag(h);
					HashtagInfoService.insertData(hashtag);
				}
			}
			if(postvo.getPost_title().length() < 30) {
				cardvo.setCard_title(postvo.getPost_title());
			} else {
				cardvo.setCard_title(postvo.getPost_title().substring(0, 30));
			}
			if(postvo.getPost_content().length() < 73) {
				cardvo.setCard_content(postvo.getPost_content());
			} else {
				cardvo.setCard_content(postvo.getPost_content().substring(0, 73));
			}
			cardvo.setLongitude(postvo.getLongitude());
			cardvo.setLatitude(postvo.getLatitude());
			cardvo.setUser_id(postvo.getUser_id());
			cardvo.setCategory_id(postvo.getCategory_id());
			cardvo.setPost_id(postvo.getPost_id());
			cardInfoService.insertData(cardvo);
			
			resVO.setRetCode(ResultData.RET_OK, messageSource);
		} catch (DataAccessException e) {
			if (DbUtils.getErrorCode(e) == DbUtils.ERR_DB_DUPLICATE_KEY) {
				resVO.setRetCode(ResultData.ERR_DB_DUPLICATE_KEY, messageSource);
			} else {
				resVO.setRetCode(ResultData.ERR_RESULT_FAIL, messageSource);
			}
			logger.error(request.getServletPath() + ", Insert Error => " + e.getMessage());
		}

		return resVO;
	}

	@RequestMapping(value="/edit_action.do",method = RequestMethod.PUT)
	public @ResponseBody BaseResult 
	postEditAction(@RequestBody PostInfoVO vo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		logger.info(vo.toString());
		int nRetCode = ResultData.ERR_RESULT_FAIL;
		CardInfoVO cardvo = new CardInfoVO();
		try {
			if (PostInfoService.updateData(vo) > 0) {
				HashtagInfoService.deleteData(vo.getPost_id());
				if (vo.getHashtags().size() == 1 && vo.getHashtags().get(0) == "") {
				} else {
					for (String h: vo.getHashtags()) {
						HashtagInfoVO hashtag = new HashtagInfoVO();
						hashtag.setHashtag(h);
						hashtag.setPost_id(vo.getPost_id());
						HashtagInfoService.insertUpdateData(hashtag);
					}
				}
				if(vo.getPost_title().length() < 30) {
					cardvo.setCard_title(vo.getPost_title());
				} else {
					cardvo.setCard_title(vo.getPost_title().substring(0, 30));
				}
				if(vo.getPost_content().length() < 73) {
					cardvo.setCard_content(vo.getPost_content());
				} else {
					cardvo.setCard_content(vo.getPost_content().substring(0, 73));
				}
				cardvo.setLongitude(vo.getLongitude());
				cardvo.setLatitude(vo.getLatitude());
				cardvo.setPost_id(vo.getPost_id());
				cardInfoService.updateData(cardvo);
				nRetCode = ResultData.RET_OK;
			}
		} catch (DataAccessException e) {
			nRetCode = ResultData.ERR_RESULT_FAIL;
			logger.error(request.getServletPath() + ", Update Error => " + e.getMessage());
		}
	      return ResultData.create(nRetCode);
	}

	@RequestMapping(value="/del_action.do",method = RequestMethod.DELETE)
	public @ResponseBody BaseResult 
	postDelAction(@RequestBody PostInfoVO postvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		if (PostInfoService.deleteData(postvo) > 0)
			nRetCode = ResultData.RET_OK;

		return ResultData.create(nRetCode);
	}
}
