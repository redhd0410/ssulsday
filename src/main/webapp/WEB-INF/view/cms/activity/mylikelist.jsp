<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>mylikelist</title>
<script type="text/javascript">
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
             <input type="hidden" id="post_id" name="post_id" value="" /> 
            
            <div class="box-body col-sm-12">
               <table id="example2"
                  class="table table-bordered table-striped table-hover"
                  style="margin-top: -30px;">
                  <thead>
                     <tr>
                        <th class="text-center" width=15%>글제목</th>
                        <th class="text-center" width=15%>내용</th>
                        <th class="text-center" width=15%>작성자id</th>
                        <th class="text-center" width=15%>닉네임</th>
                        <th class="text-center" width=15%>작성시간</th>
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
                                 <td class="text-center"><c:out
                                          value="${item.card_title}"></c:out></td>
                                 <td class="text-center"><c:out value="${item.card_content}"></c:out></td>
                                 <td class="text-center"><c:out value="${item.user_id}"></c:out></td>
                                 <td class="text-center"><c:out value="${item.time_created}"></c:out></td>
                              </tr>
                           </c:forEach>
                        </c:otherwise>
                     </c:choose>
                  
                  </tbody>
               </table>
            </div>
         </form:form> 
      </div>
   </div>
</body>
</html>