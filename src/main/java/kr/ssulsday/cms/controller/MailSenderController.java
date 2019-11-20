package kr.ssulsday.cms.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
import kr.ssulsday.cms.vo.MailVO;
import kr.ssulsday.cms.vo.UserInfoVO;

@Controller
@RequestMapping(value="/cms/contact")
public class MailSenderController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailSenderController.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="/send_action.do", method=RequestMethod.POST)
	public @ResponseBody BaseResult sendAction(@RequestBody MailVO mail, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: POST " + request.getServletPath());
		int		nRetCode	= ResultData.ERR_RESULT_FAIL;
		
		String receiver = mail.getReceiver();
		String title = mail.getTitle();
		String content = mail.getContent();
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom("yapp.ssulsday@gmail.com");
			messageHelper.setTo("yapp.ssulsday@gmail.com");
			messageHelper.setSubject(title);
			messageHelper.setText("보낸 사람: " + receiver + "\n" + "내용: " + content);

			
			mailSender.send(message);
			
			nRetCode = ResultData.RET_OK;
			
		} catch (DataAccessException e) {
			nRetCode = ResultData.ERR_RESULT_FAIL;
			logger.error(request.getServletPath() + ", Send Error => " + e.getMessage());
		}

		return ResultData.create(nRetCode);
	}
	
}