package kr.ssulsday.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.cubex.comm.vo.PagingListVO;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.ComStr;
import kr.cubex.utils.DbUtils;
import kr.ssulsday.cms.service.UserInfoService;
import kr.ssulsday.cms.vo.UserInfoVO;
import kr.ssulsday.comm.web.TempKey;

@Controller
@RequestMapping(value="/cms/user")
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Resource
	private UserInfoService userinfoService;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
			String authkey = new TempKey().getKey(50, false);
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			userVO.setAuthkey(authkey);
			userinfoService.insertData(userVO);

			messageHelper.setFrom("yapp.ssulsday@gmail.com");
			messageHelper.setTo(userVO.getUser_id());
			messageHelper.setSubject("[Ssulsday] 이메일을 확인 해 주세요.");
			messageHelper.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
					.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://15.164.218.21:8080/cms/user/join.do?email=")
					.append(userVO.getUser_id())
					.append("&authkey=")
					.append(authkey)
					.append("' target='_blenk'>이메일 인증 확인</a>")
					.toString());

			mailSender.send(message);
			
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
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public @ResponseBody
	BaseResult userJoin(@RequestParam String email, @RequestParam String authkey, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		ResultData resVO	= new ResultData();
		
		try {
			UserInfoVO userVO = new UserInfoVO();
			if (authkey.equals(userinfoService.selectData(email).getAuthkey())) {
				userVO.setIs_active(1);
				userVO.setUser_id(email);
				userinfoService.updateAuthStatus(userVO);
				resVO.setRetCode(ResultData.RET_OK, messageSource);
			} 
			else {
				resVO.setRetCode(ResultData.ERR_RESULT_FAIL, messageSource);
			}
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
