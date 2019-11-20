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
<title>Edit post here</title>
<script type="text/javascript">
   function goList() {
      document.frmReg.action = '/cms/post/list.do';
      document.frmReg.submit();
   }
   function goView() {
      document.frmReg.action = '/cms/post/view.do';
      document.frmReg.submit();
   }
   function doEditAction() {

      var edyn = confirm('수정하시겠습니까?');
      if (edyn) {
         var url = '/cms/post/edit_action.do';
         var params = {
            'post_id' : $('#post_id').val(),
            'post_title' : $('#post_title').val(),
            'post_content' : $('#post_content').val(),
            'hashtags' : $('#hashtag').val().split(",")
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
   }
</script>
</head>
<body>
   <div class="wrapper">
      <!-- Main content -->
      <section class="content">
         <div class="row">
            <div class="col-xs-12">
               <div class="box">
                  <div class="box-body" style="margin-top: 20px;">
                     <form:form commandName="searchPage" id="frmReg" name="frmReg"
                        method="post" class="form-horizontal">
                        <input type="hidden" id="post_id" name="post_id"
                           value="${result.post_id}" />
                        <table class="table table-bordered">
                           <tr>
                              <td class="active text-c-enter"
                                 style="vertical-align: middle; width: 20%;">* 타이틀</td>
                              <td><input class="form-control" style="width: 200px;"
                                 id="post_title" name="post_title" type="text"
                                 value="${result.post_title }" /></td>
                           </tr>
                           <tr>
                              <td class="active text-center"
                                 style="vertical-align: middle; width: 20%;">* 내용</td>
                              <td><input class="form-control" style="width: 200px;"
                                 id="post_content" name="post_content" type="text"
                                 value="${result.post_content }" /></td>
                           </tr>
                           	<tr>
								<td class="active text-center"
									style="vertical-align: middle; width: 20%;">* 해쉬태그 </td>
								<td><input class="form-control" style="width: 200px;"
									id="hashtag" name="hashtag" type="text" value="" /></td>
							</tr>
                        </table>
                        <div style="text-align: center; margin-top: 20px;">
                        <a href="javascript:doEditAction();" class="btn btn-success" style="margin-right:10px;">수정</a>
                           <a href="javascript:goView();" class="btn btn-default"
                              style="margin-right: 10px;">취소</a>
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
      <!-- /.content -->
   </div>

</body>
</html>