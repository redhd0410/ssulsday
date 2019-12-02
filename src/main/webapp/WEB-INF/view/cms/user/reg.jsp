<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script src='/resources/cms/js/utils/com_network.js'></script>
<script src='/resources/cms/js/utils/com_codes.js'></script>
<meta charset="UTF-8">
<title>Reg User</title>
<script  type="text/javascript">
	function goList() {
		location.href = '/cms/user/list.do';
	}
	function doRegAction() {

		var url = '/cms/user/reg_action.do';

		var user_id = $('#user_id').val();
		var user_pwd = $('#user_pwd').val();
		var username = $('#username').val();

		var params = {
			'user_id' : user_id,
			'user_pwd' : user_pwd,
			'username' : username
		};

		console.log(params);
		net_ajax(url, params, function(data) {
			if (data.retCode == const_ret_ok) {
				goList();
			} else if (data.retCode == 606) {
				alert('중복된 데이터 입니다.');
			} else {
				alert(data.retMsg);
			}
		}, onNetCallbackDefaultError);
	}
</script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body" style="margin-top: 20px;">
							<form:form commandName="searchPage" id="frmReg" name="frmReg"
								method="post" class="form-horizontal">
								<!-- /.row -->
								<table class="table table-bordered">
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 아이디</td>
										<td><input class="form-control" style="width: 200px;"
											id="user_id" name="user_id" type="text" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 비밀번호</td>
										<td><input class="form-control" style="width: 200px;"
											id="user_pwd" name="user_pwd" type="password" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 닉네임 </td>
										<td><input class="form-control" style="width: 200px;"
											id="username" name="username" type="text" value="" /></td>
									</tr>
								</table>
								<!-- 모달 팝업 시작 -->

								<div style="text-align: center; margin-top: 20px;">
									<a href="javascript:doRegAction();" class="btn btn-default"
										style="margin-right: 10px;">등록</a> <a
										href="javascript:goList();" class="btn btn-default"
										style="margin-right: 10px;">취소</a>
								</div>

								<form:hidden path="pageIndex" />
							</form:form>
							<!-- /.container-fluid -->
							<br>
						</div>
					</div>
					<!-- /.box -->
				</div>
			</div>
		</section>
		<!-- /.Main content -->

	</div>
</body>
</html>