<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script src='/resources/cms/js/utils/com_network.js'></script>
<script src='/resources/cms/js/utils/com_codes.js'></script>
<meta charset="UTF-8">
<title>Delete title here</title>
<script type="text/javascript">

function goList() {
	document.regForm.action = '/cms/user/list.do';
	document.regForm.submit();
}

function onClickTrans(flag, ukey) {
	var f	= document.frmView;

	if (flag == 'L') {
		f.action			= '/cms/user/list.do';
	} else if (flag == 'E') {
		f.action			= '/cms/user/edit.do';
	}
	f.submit();
}

function doDelAction() {
	var ukey		= $('#user_id').val();
	var delyn		= confirm('삭제하시겠습니까?');
	if (delyn) {
		var url = '/cms/user/del_action.do';
		var params = {
				user_id:	ukey
		};
		net_ajax(url, params, function(data) {
			if (data.retCode == const_ret_ok) {
				location.href = '/cms/user/list.do';
			} else {
				alert(data.retMsg);
			}
		}, onNetCallbackDefaultError);
	}
}

</script>
</head>
<body>
<div class="content-wrapper">
			<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body" style="margin-top:20px;">
							<form:form commandName="searchPage" id="frmView" name="frmView" method="post" class="form-horizontal">
								<input type="hidden" id="user_id" name="user_id" value="${result.user_id}" />
								<table class="table table-bordered">
									<tr>
										<td class="active text-center" style="vertical-align: middle; width:20%;">아이디</td>
										<td>
											<c:out value="${result.user_id}"/>
										</td>
									</tr>
									<tr>
										<td class="active text-center" style="vertical-align: middle;">이메일</td>
										<td>
											<c:out value="${result.username}"/>
										</td>
									</tr>
								</table>
								<div style="text-align: center;margin-top:20px;">
									<button class="btn btn-default" id="user" style="margin-right:10px;" onClick="onClickTrans('E', '${result.user_id}');">수정</button>
									<button class="btn btn-default" id="user" style="margin-right:10px;" onClick="onClickTrans('L');">목록</button>
									<button class="btn btn-success" id="user" style="margin-right:10px;" onClick="doDelAction();">삭제</button>
									
								</div>
							<!-- 검색조건 유지 -->
							<form:hidden path="searchCondition" />
							<form:hidden path="searchKeyword" />
							<form:hidden path="pageIndex" />
							</form:form>
							<!-- /.container-fluid -->
							<br>
					    </div>
				    </div><!-- /.box -->
				</div>
			</div>
			</section><!-- /.Main content -->
		</div><!-- /.content-wrapper -->
</body>
</html>