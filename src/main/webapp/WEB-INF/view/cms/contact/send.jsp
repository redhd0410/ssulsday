<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MailSender</title>
</head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<script src='/resources/cms/js/utils/com_network.js'></script>
<script src='/resources/cms/js/utils/com_codes.js'></script>

<body>
 <h4>문의 메일 보내기 </h4>
    <form action="/cms/contact/send_action.do" id="frmReg" name="frmReg" method="post">
      <div>
        <input type="email" name="receiver" size="120"
        style="width: 100%" placeholder="이메일을 입력해 주세요"
        class="form-control">
      </div>
      <div align="center">
        <!-- 제목 -->
        <input type="text" name="title" size="120"
        style="width: 100%" placeholder="제목을 입력해주세요"
        class="form-control">
      </div>
        <p>
          <div align="center">
            <textarea name="content" cols="120" rows="12"
            style="width: 100%; resize: none" placeholder="문의 내용"
            class="form-control"></textarea>
          </div>
        <p>
      <div align="center">
        <input type="submit" value="메일 보내기" class="btn btn-warning" onClick="doSendAction();" >
      </div>
    </form>

</body>
</html>