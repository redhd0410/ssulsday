package kr.ssulsday.cms.controller;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import io.swagger.annotations.ApiOperation;
import kr.cubex.comm.vo.SearchPageVO;
import kr.cubex.data.BaseResult;
import kr.cubex.data.ResultData;
import kr.cubex.utils.ComStr;

import kr.ssulsday.cms.service.UserInfoService;
import kr.ssulsday.cms.service.LoginService;
import kr.ssulsday.cms.vo.UserInfoVO;
import kr.ssulsday.cms.vo.LoginVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private LoginService loginService;

	@Resource
	private UserInfoService userInfoService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ApiOperation(value="로그아웃", nickname="로그아웃")
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	@ResponseBody
	BaseResult logoutForm(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		Object obj = session.getAttribute("login");
		ResultData resVO = ResultData.create(BaseResult.ERR_LOGIN_FAILED);
		if (obj != null) {
			UserInfoVO vo = (UserInfoVO) obj;
			// null이 아닐 경우 제거
			session.removeAttribute("login");
			session.invalidate();// 세션 전체를 날려버림
			// 쿠키를 가져와보고
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				// null이 아니면 존재하면!
				loginCookie.setPath("/");
				// 쿠키는 없앨 때 유효시간을 0으로 설정하는 것 !!! invalidate같은거 없음.
				loginCookie.setMaxAge(0);
				// 쿠키 설정을 적용한다.
				response.addCookie(loginCookie);

				// 사용자 테이블에서도 유효기간을 현재시간으로 다시 세팅해줘야함.
				Date date = new Date(System.currentTimeMillis());
				loginService.keepLogin(vo.getUser_id(), session.getId(), date);
				resVO = ResultData.create(BaseResult.RET_OK);
			}
		}

		return resVO;
	}

	@ApiOperation(value="로그인", nickname="로그인")
	@RequestMapping(value = "/login_action.do", method = RequestMethod.POST)
	@ResponseBody
	BaseResult loginAction(HttpSession session, @RequestBody LoginVO dataVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		logger.info("LoginController::loginAction() - login_id : " + dataVO.getUser_id() + " login_pwd : "
				+ dataVO.getUser_pwd());

		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}

		ResultData resVO = null;
		String pwd = userInfoService.selectpwdData(dataVO.getUser_id());
		String rawpwd = dataVO.getUser_pwd();

		if (passwordEncoder.matches(rawpwd, pwd)) {
			UserInfoVO userVO = loginService.selectUserLogin(dataVO);

			if (userVO == null) {
				resVO = ResultData.create(BaseResult.ERR_ID_NOT_FOUND, messageSource);
			} else {
				resVO = ResultData.create(BaseResult.RET_OK);
				session.setAttribute("login", userVO);
				if (dataVO.getisUseCookie()) {
					Cookie cookie = new Cookie("loginCookie", session.getId());
					// 쿠키를 찾을 경로를 컨텍스트 경로로 변경해
					cookie.setPath("/");
					int amount = 60 * 60 * 24 * 7;
					cookie.setMaxAge(amount);
					response.addCookie(cookie);
					Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
					// 현재 세션 id와 유효시간을 사용자 테이블에 저장한다.
					loginService.keepLogin(userVO.getUser_id(), session.getId(), sessionLimit);

				}
			}
			logger.info(request.getServletPath() + " ==> retCode : " + resVO.getRetCode());

			if (resVO.isRetOK()) {
				logger.info("user_id : " + userVO.getUser_id());
				logger.info("user_name : " + userVO.getUsername());
			}

		}
		else {
			resVO = ResultData.create(BaseResult.ERR_LOGIN_FAILED);
		}
		
		return resVO;
	}
}
