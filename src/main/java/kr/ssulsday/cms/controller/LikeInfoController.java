package kr.ssulsday.cms.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.LikeInfoService;
import kr.ssulsday.cms.service.PostInfoService;
import kr.ssulsday.cms.service.CardInfoService;
import kr.ssulsday.cms.vo.LikeInfoVO;
import kr.ssulsday.cms.vo.PostInfoVO;

@Controller
@RequestMapping(value="/cms/like")
public class LikeInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(LikeInfoController.class);
	
	@Resource
	private LikeInfoService LikeInfoService;
	
	@Resource
	private PostInfoService PostInfoService;
	
	@Resource
	private CardInfoService CardInfoService;
	
	@Autowired
	private	MessageSource messageSource;
	@RequestMapping(value = "/reg_action.do",method = RequestMethod.POST)
	@ResponseBody BaseResult likeCreateAction(@RequestBody LikeInfoVO likevo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO = new ResultData();
		try {
			LikeInfoService.insertData(likevo);
			PostInfoService.updateCount(likevo.getPost_id(), LikeInfoService.selectDataCount(likevo), "L");
			CardInfoService.updateCount(likevo.getPost_id(), LikeInfoService.selectDataCount(likevo), "L");
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

	@RequestMapping(value="/del_action.do",method = RequestMethod.DELETE)
	@ResponseBody BaseResult likeDelAction(@RequestBody LikeInfoVO likevo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int nRetCode = ResultData.ERR_RESULT_FAIL;

		if (LikeInfoService.deleteData(likevo) > 0) {
			nRetCode = ResultData.RET_OK;
		}
		return ResultData.create(nRetCode, messageSource);
	}
	
	@RequestMapping(value="/view.do", method = RequestMethod.POST)
	@ResponseBody BaseResult viewAction(@RequestBody SearchPageVO searchVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		int nRetCode = ResultData.ERR_NO_DATA;
		
		if (LikeInfoService.selectUserLiked(searchVO) > 0) {
			nRetCode = ResultData.RET_OK;
		}
		return ResultData.create(nRetCode, messageSource);
	}

}
