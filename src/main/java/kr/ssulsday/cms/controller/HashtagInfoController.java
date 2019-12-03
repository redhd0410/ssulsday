package kr.ssulsday.cms.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/cms/hashtag")
public class HashtagInfoController {

	private static final Logger logger = LoggerFactory.getLogger(HashtagInfoController.class);

	@Resource
	private HashtagInfoService HashtagInfoService;

	private final ReentrantLock locker = new ReentrantLock();

	@ApiOperation(value="검색", nickname="검색")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "searchKeyword", value = "검색 키워드", required = false, dataType = "integer", paramType = "path")
	})
	@RequestMapping(value = { "/list.do/{searchKeyword}"}, method = RequestMethod.GET)
	public @ResponseBody List<?> postListForm(@PathVariable String searchKeyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info(">>>>> REQ-URI: " + request.getServletPath());
		
		String searchDecoded = URLDecoder.decode(searchKeyword, "EUC-KR");
		SearchPageVO searchVO = new SearchPageVO();
		
		searchVO.setSearchKeyword(searchDecoded);

		PagingListVO listVO = HashtagInfoService.selectListPage(searchVO);

		List<CardInfoVO> cardList = (List<CardInfoVO>) listVO.getItems();
		for (CardInfoVO vo : cardList) {
			List<HashtagInfoVO> hashList = HashtagInfoService.selectHashtagListData(vo.getPost_id());
			List<String> hashtagList = new ArrayList<String>();
			for (HashtagInfoVO hashtagVO : hashList) {
				if (hashtagVO.getHashtag().length() > 1 && hashtagVO.getHashtag()!=null) {
					logger.info(hashtagVO.getHashtag());
					hashtagList.add(hashtagVO.getHashtag());
				}
			}
			vo.setHashtags(hashtagList);
		}
		
		return listVO.getItems();

	}
}
