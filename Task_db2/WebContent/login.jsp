<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 <%@ page import="jp.co.axiz.web.servlet.LoginServlet"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<c:if test="${not empty msg3}">
        <p class="error">${msg3}</p>
        </c:if>


  <form action="login" method="post">
    <fieldset>
      <div>
        <label>ID</label>
        <input type="text" name="loginId">
        <c:if test="${not empty msg1}">
        <span class="error">${msg1}</span>
        </c:if>
      </div>
      <div>
        <label>PASS</label>
        <input type="password" name="pass">
        <c:if test="${not empty msg2}">
        <span class="error">${msg2}</span>
        </c:if>
      </div>

    </fieldset>
    <button type="submit">ログイン</button>
  </form>
  <div>
    <a href="index.jsp">TOP画面に戻る</a>
  </div>
</body>
</html>
