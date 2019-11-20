package kr.ssulsday.cms.controller;

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
import kr.ssulsday.cms.service.UserInfoService;
import kr.ssulsday.cms.vo.UserInfoVO;

@Controller
@RequestMapping(value="/cms/user")
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Resource
	private UserInfoService userinfoService;
	
	@Autowired
	private	MessageSource messageSource;
	
	/**
	 * 처리 내용 : 관리자 리스트를 화면에 출력
	 */
	@RequestMapping(value="/list.do",method = RequestMethod.GET)
	public @ResponseBody List<?>
	userListForm(@ModelAttribute("searchVO") SearchPageVO searchVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		PagingListVO	listVO = userinfoService.selectListPage(searchVO);
		
		return listVO.getItems();
		
	}
	
	
	/**
	 * 처리 내용 : 행사단체 수정 페이지로 이동 처리 및 정보 표시
	 */
	@RequestMapping(value = "/view.do/{user_id}",method = RequestMethod.GET)
	public @ResponseBody UserInfoVO
	userViewForm(@ModelAttribute("searchVO") SearchPageVO searchVO, @PathVariable String user_id, ModelMap model, HttpServletRequest request) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
//		BaseSessInfo 	info	= SessionUtility.getLoginForAdmin(request);
//		
//		if (info == null) {
//			return AdminSessInfo.REDIRECT_URI_CMS_LOGIN;
//		}
		UserInfoVO userVO	= userinfoService.selectData(user_id);
		
		return userVO;
	}
	
	/**
	 * 처리 내용 : 관리자 등록 정보에 대한 데이터 처리
	*/
	@RequestMapping(value = "/reg_action.do",method = RequestMethod.POST)
	public @ResponseBody
	BaseResult userRegAction(@RequestBody UserInfoVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO	= new ResultData();
		
		try {
			userinfoService.insertData(userVO);
			resVO.setRetCode(ResultData.RET_OK, messageSource);
		} catch (DataAccessException e) {
			if (DbUtils.getErrorCode(e) == DbUtils.ERR_DB_DUPLICATE_KEY) {
				resVO.setRetCode(ResultData.ERR_DB_DUPLICATE_KEY, messageSource);
			}
			else {
				resVO.setRetCode(ResultData.ERR_RESULT_FAIL, messageSource);
			}
			logger.error(request.getServletPath() + ", Insert Error => " + e.getMessage());
		}

		return resVO;
	}
	
	
	/**
	 * 처리 내용 : 관리자 수정 정보에 대한 데이터 처리
	 */
	@RequestMapping(value = "/edit_action.do", method = RequestMethod.PUT)
	public @ResponseBody
	 BaseResult userEditAction(@RequestBody UserInfoVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int		nRetCode	= ResultData.ERR_RESULT_FAIL;
		try {
			if (userinfoService.updateData(userVO) > 0)
			nRetCode = ResultData.RET_OK;
		} catch (DataAccessException e) {
			nRetCode = ResultData.ERR_RESULT_FAIL;
			logger.error(request.getServletPath() + ", Update Error => " + e.getMessage());
		}

		return ResultData.create(nRetCode);
	}

	@RequestMapping(value="/del_action.do",method = RequestMethod.DELETE)
	public @ResponseBody
	BaseResult userDelAction(@RequestBody UserInfoVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		int		nRetCode	= ResultData.ERR_RESULT_FAIL;
		
		if (userinfoService.deleteData(userVO) > 0)
			nRetCode = ResultData.RET_OK;
		
		return ResultData.create(nRetCode);
	}
}
