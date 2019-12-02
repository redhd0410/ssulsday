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
<title>Delete Post here</title>
<script type="text/javascript">
	function goList() {
		document.regForm.action = '/cms/post/list.do';
		document.regForm.submit();
	}
	function doRegAction() {

		var url = '/cms/comment/reg_action.do';

		var post_id = $('#post_id').val();
		var comment_content = $('#comment_content').val();
		var user_id = 'test_user'

		var params = {
			'post_id' : post_id,
			'comment_content' : comment_content,
			'user_id' : user_id
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
	//좋아요부분
	function onClickAdd(flag) {
		var ukey = $('#post_id').val();
		var userid = 'test_user'
		if (flag == 'L') {
			var url = '/cms/like/reg_action.do';
			var params = {
				post_id : $('#post_id').val(),
				user_id : userid
			};

			console.log(params);

			net_ajax(url, params, function(data) {
				if (data.retCode == const_ret_ok) {
					/* location.href = '/cms/post/view.do'; */
					location.reload();
				} else {
					alert(data.retMsg);
				}
			}, onNetCallbackDefaultError);
		} else if (flag == 'D') {
			var url = '/cms/like/del_action.do';
			var params = {
				'post_id' : $('#post_id').val(),
				'user_id' : 'test_user'
			};

			net_ajax(url, params, function(data) {
				if (data.retCode == const_ret_ok) {
					location.href = '/cms/post/view.do';
				} else {
					alert(data.retMsg);
				}
			}, onNetCallbackDefaultError);

		}
	}
	//목록, 수정, 댓글이동
	function onClickTrans(flag, ukey) {
		var f = document.frmView;

		if (flag == 'L') {
			f.action = '/cms/post/list.do';
		} else if (flag == 'E') {
			f.action = '/cms/post/edit.do';

		} else if(flag = 'C'){
			f.post_id.value = ukey;
			f.action ='/cms/comment/list.do'
		}
		f.submit();
	}

	function doDelAction() {
		var ukey = $('#post_id').val();
		var delyn = confirm('삭제하시겠습니까?');
		if (delyn) {
			var url = '/cms/post/del_action.do';
			var params = {
				post_id : ukey
			};
			net_ajax(url, params, function(data) {
				if (data.retCode == const_ret_ok) {
					location.href = '/cms/post/list.do';
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
						<div class="box-body" style="margin-top: 20px;">
							<form:form commandName="searchPage" id="frmView" name="frmView"
								method="post" class="form-horizontal">
								<input type="hidden" id="post_id" name="post_id"
									value="${result.post_id}" />
								<table class="table table-bordered">
									<tr>
										<td class="active text-center"
											style="vertical-align: middle; width: 20%;">글제목</td>
										<td><c:out value="${result.post_title}" /></td>
									</tr>
									<tr>
										<td class="active text-center" style="vertical-align: middle;">글내용</td>
										<td><c:out value="${result.post_content}" /></td>
									</tr>
									<tr>
										<td class="active text-center" style="vertical-align: middle;">좌표
										</td>
										<td><c:out value="${result.latitude }" /> <c:out
												value="${result.longitude }" /></td>
									</tr>
									<tr>
										<td class="active text-center" style="vertical-align: middle;">조회수</td>
										<td><c:out value="${result.viewcount}" /></td>
									</tr>
									<tr>
										<td class="active text-center" style="vertical-align: middle;">좋아요수</td>
										<td><c:out value="${result.likecount}" /></td>
									</tr>
								</table>
								<table>
								<c:forEach var="item" items="${resultList}" varStatus="status">
										<c:set
											value="${(nTotalRecordCnt - ((nCurrPageNo - 1) * nRecordCntPerPage)) - status.index }"
											var="nIndexCnt"></c:set>
										<tr>
											<td class="text-center"><c:out value="${item.hashtag}"></c:out></td>
										</tr>
								</c:forEach>
								</table>
								<div>
									<br> <a href="javascript:onClickAdd('L');"
										class="btn btn-success" style="margin-right: 10px;">좋아요</a>
									<!-- <button class="btn btn-default" id="like" style="margin-right:10px;" onClick="onClickAdd('L');">좋아요</button> -->
									<a href="javascript:onClickAdd('D');" class="btn btn-success"
										style="margin-right: 10px;">좋아요취소</a>
								</div>
								<div style="text-align: center; margin-top: 20px;">
									<button class="btn btn-default" id="post"
										style="margin-right: 10px;"
										onClick="onClickTrans('E', '${result.post_id}');">수정</button>
									<button class="btn btn-default" id="post"
										style="margin-right: 10px;" onClick="onClickTrans('L');">목록</button>
									<button class="btn btn-success" id="post"
										style="margin-right: 10px;" onClick="doDelAction();">삭제</button>
										<button class="btn btn-default" id="post"
										style="margin-right: 10px;" onClick="onClickTrans('C','${result.post_id}');">댓글</button>

								</div>

								<!-- 검색조건 유지 -->
								<form:hidden path="searchCondition" />
								<form:hidden path="searchKeyword" />
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
	<!-- /.content-wrapper -->

</body>
</html>