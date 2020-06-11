<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="jp.co.axiz.web.servlet.update.UpdateServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    更新するIDを入力してください<br>
    <span class="required"></span>は必須です
  </p>

<c:if test="${not empty msg}">
  <p class="error">${msg}</p>
    </c:if>

  <form action="update" method="post">
    <fieldset>
      <div>
        <label class="required">ID</label>
        <input type="text" name="loginId">
         <c:if test="${not empty msg1}">
        <span class="error">${msg1}</span>
    </c:if>
      </div>
    </fieldset>
    <button type="submit">確認</button>
  </form>
  <div>
    <a href="menu.jsp">メニューに戻る</a>
  </div>
</body>
</html>
