<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/cms/js/utils/com_network.js"></script>
<script src="/resources/cms/js/utils/com_codes.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>comment list</title>
<script type="text/javascript">
function doRegAction() {

	var url = '/cms/comment/reg_action.do';
	var post_id = $('#post_id').val();
	var comment_content = $('#comment_content').val();
	var user_id		= $('#user_id').val();

	var params = {
		'post_id' : post_id
		,'comment_content' : comment_content
		,'user_id' : user_id
		};

	console.log(params);
	
	net_ajax(url, params, function(data) {
		if (data.retCode == const_ret_ok) {
			location.reload(true);
		} else {
			alert(data.retMsg);
		}
	}, onNetCallbackDefaultError);
}
function onClickTrans(flag, ukey) {
	var f = document.frmList;
	if (flag == 'V') {
		f.comment_id.value = ukey;
		f.action = '/cms/comment/view.do';
	}else if(flag =='E'){
			f.comment_id.value = ukey;
			f.user_id.value = 'sdasdads';
			f.action = '/cms/comment/edit.do';
	}
	f.submit();
}

function doDelAction(ukey){
	console.log(ukey);
	var user_id = 'test_user';
	var delyn = confirm('삭제하시겠습니까?');
	if (delyn) {
		var url = '/cms/comment/del_action.do';
		var params = {
			comment_id : ukey,
			user_id : user_id
		};

		net_ajax(url, params, function(data) {
			if (data.retCode == const_ret_ok) {
				location.reload(true);
			} else {
				alert(data.retMsg);
			}
		}, onNetCallbackDefaultError);
	}
	
}
	function searchAction() {
		document.frmList.action = "<c:url value='/cms/post/list.do'/>";
		document.frmList.submit();
	}
	function onsubmitSearch(thiz) {
		alert(thiz.post);
		return false;
	}
	/* pagination 페이지 링크 function */
	function fn_egov_link_page(pageNo) {
		document.frmList.pageIndex.value = pageNo;
		document.frmList.action = "<c:url value='/cms/post/list.do'/>";
		document.frmList.submit();
	}
</script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<BR><br><br>
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<form:form commandName="searchPage" name="frmList" method="post">
				<input type="hidden" id="comment_id" name="comment_id" value="" />
				<div class="box-body col-sm-12">
					<table id="example2"
						class="table table-bordered table-striped table-hover"
						style="margin-top: -30px;">
						<thead>
							<tr>
							<th class="text-center" width=15%>글번호</th>
							<th class="text-center" width=15%>댓글번호</th>
								<th class="text-center" width=15%>댓글내용</th>
								<th class="text-center" width=15%>댓글 작성자</th>
								<th class="text-center" width=15%>작성시간</th>
								<th class="text-center" width=15%>삭제/수정</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(resultList) <= 0}">
									<tr>
										<td colspan="7" class="text-center">데이터가 존재하지 않습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${resultList}" varStatus="status">
										<c:set
											value="${(nTotalRecordCnt - ((nCurrPageNo - 1) * nRecordCntPerPage)) - status.index }"
											var="nIndexCnt"></c:set>
										<tr>
											<td class="text-center"><c:out value="${item.post_id}"></c:out></td>
											<td class="text-center"><a
												href="javascript:onClickTrans('V', '${item.comment_id}');"><c:out value="${item.comment_id}"></c:out></a></td>
											<td class="text-center"><c:out value="${item.comment_content}"></c:out></td>
											<td class="text-center"><c:out value="${item.user_id}"><input type="hidden" id= "user_id" name="user_id" value=""/></c:out></td>
											
											<td class="text-center"><c:out value="${item.comment_write_time}"></c:out></td>
											<td class="text-center">
											<button class="btn btn-success" id="comment" style="margin-right: 10px;" onClick="doDelAction('${item.comment_id}');">삭제</button>
											<button class="btn btn-default" id="comment" style="margin-right: 10px;" onClick="onClickTrans('E', '${item.comment_id}');">수정</button>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						
						</tbody>
					</table>
				</div>
				<form:form commandName="searchPage" id="frmReg" name="frmReg" method="post">
				<table class="table table-bordered">
				<thead>
							<tr>
							<th class="text-center" width=15%>작성자</th>
								<th class="text-center" width=15%>댓글내용</th>
							</tr>
						</thead>
				<tr>
				<td><input type="text" id="user_id" name="user_id" value="test_user" /></td>
				<td><input class="form-control" style="width: 200px;" id="comment_content" name="comment_content" type="text" value="" /></td>
				</tr>
				</table>
				<a href="javascript:doRegAction();" class="btn btn-default" style="margin-right: 10px;">등록</a> 
				</form:form>
			</form:form>
			</div>
			
	</div>
</body>
</html>
