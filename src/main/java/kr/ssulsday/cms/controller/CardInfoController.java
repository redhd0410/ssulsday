package kr.ssulsday.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.CardInfoService;
import kr.ssulsday.cms.service.HashtagInfoService;
import kr.ssulsday.cms.vo.CardInfoVO;
import kr.ssulsday.cms.vo.HashtagInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;


@RestController
@RequestMapping(value = "/cms/card")
public class CardInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CardInfoController.class);

	@Resource
	private CardInfoService CardInfoService;
	@Resource
	private HashtagInfoService HashtagInfoService;
	@Autowired
	private MessageSource messageSource;

	@ApiOperation(value="전체 카드 리스트 조회", nickname="전체 카드 리스트 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "category_id", value = "카테고리", required = false, dataType = "integer", paramType = "query"),
		@ApiImplicitParam(name = "searchCondition", value = "정렬 기준", required = false, dataType = "string", paramType = "query")
	})
	@RequestMapping(value = "list.do", method=RequestMethod.GET)
	public @ResponseBody List<?>  
	CardListForm(@RequestParam Optional<String> searchCondition, @RequestParam Optional<Integer> category_id, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		SearchPageVO searchVO = new SearchPageVO();
		
		if (searchCondition.isPresent()) {
			searchVO.setSearchCondition(searchCondition.get());
		}
		if (category_id.isPresent()) {
			searchVO.setSearchCondition2(category_id.get());;
		}
		
		PagingListVO listVO = CardInfoService.selectListPage(searchVO);
		
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
	
	@ApiOperation(value="반경에 있는 카드 조회", nickname="반경에 있는 카드 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "latitude", value = "위도", required = true, dataType = "number", paramType = "query"),
		@ApiImplicitParam(name = "longitude", value = "경도", required = true, dataType = "number", paramType = "query")
	})
	@RequestMapping(value = "/range.do", method=RequestMethod.GET)
	public @ResponseBody List<?>  
	CardListFormByRange(@RequestParam double latitude, @RequestParam double longitude, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		List<CardInfoVO> listVO = (List<CardInfoVO>) CardInfoService.selectListDataByRange(latitude, longitude);
		
		for (CardInfoVO vo:listVO) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO:hashList) {
				hashtagList.add(hashtagVO.getHashtag());
			}
			vo.setHashtags(hashtagList);
		}

		return listVO;

	}

	@RequestMapping(value="/view.do/{post_id}", method=RequestMethod.GET)
	public @ResponseBody CardInfoVO
	CardViewForm(@PathVariable int post_id, ModelMap model, HttpServletRequest request) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		CardInfoVO cardVO = CardInfoService.selectData(post_id);
		List<HashtagInfoVO> listVO = HashtagInfoService.selectHashtagListData(cardVO.getPost_id());
		
		List<String> hashtagList = new ArrayList<String>();
		for (HashtagInfoVO vo:listVO) {
			hashtagList.add(vo.getHashtag());
		}
		cardVO.setHashtags(hashtagList);

		return cardVO;
	}

	@RequestMapping(value = "/reg_action.do",method = RequestMethod.POST)
	public @ResponseBody BaseResult CardCreateAction(@RequestBody CardInfoVO cardvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO = new ResultData();

		try {
			CardInfoService.insertData(cardvo);
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
	public @ResponseBody BaseResult CardEditAction(@RequestBody CardInfoVO vo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		try {
			if (CardInfoService.updateData(vo) > 0)
				nRetCode = ResultData.RET_OK;
		} catch (DataAccessException e) {
			nRetCode = ResultData.ERR_RESULT_FAIL;
			logger.error(request.getServletPath() + ", Update Error => " + e.getMessage());
		}

		return ResultData.create(nRetCode);
	}

	@RequestMapping(value="/del_action.do",method = RequestMethod.DELETE)
	public @ResponseBody BaseResult postDelAction(@RequestBody CardInfoVO cardvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		if (CardInfoService.deleteData(cardvo) > 0)
			nRetCode = ResultData.RET_OK;

		return ResultData.create(nRetCode);
	}
	

}
