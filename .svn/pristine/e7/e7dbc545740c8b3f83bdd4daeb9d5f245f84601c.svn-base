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
<title>Reg post</title>
<script  type="text/javascript">
	function goList() {
		location.href = '/cms/post/list.do';
	}
	function doRegAction() {

		var url = '/cms/post/reg_action.do';

		var post_title = $('#post_title').val();
		var post_content = $('#post_content').val();
		var user_id		= $('#user_id').val();
		var latitude		= $('#latitude').val();
		var longitude		= $('#longitude').val();
		var category_id    = $('#category_id').val();
		var hashtags = $('#hashtag').val().split(",");

		var params = {
			'post_title' : post_title
			,'post_content' : post_content
			,'user_id' : user_id
			,'category_id':category_id
			,'latitude': latitude
			,'longitude': longitude
			,'hashtags':hashtags
		};

		console.log(params);
		net_ajax(url, params, function(data) {
			if (data.retCode == const_ret_ok) {
				goList();
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
											style="vertical-align: middle; width: 20%;">*작성자 id</td>
										<td><input class="form-control" style="width: 200px;"
											id="user_id" name="user_id" type="text" value="test_user" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">*카테고리</td>
										<td><input class="form-control" style="width: 200px;"
											id="category_id" name="category_id" type="text" value="1" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 제목</td>
										<td><input class="form-control" style="width: 200px;"
											id="post_title" name="post_title" type="text" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 내용</td>
										<td><input class="form-control" style="width: 200px;"
											id="post_content" name="post_content" type="text" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 위도 </td>
										<td><input class="form-control" style="width: 200px;"
											id="latitude" name="latitude" type="text" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 경도 </td>
										<td><input class="form-control" style="width: 200px;"
											id="longitude" name="longitude" type="text" value="" /></td>
									</tr>
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">* 해쉬태그 </td>
										<td><input class="form-control" style="width: 200px;"
											id="hashtag" name="hashtag" type="text" value="" /></td>
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