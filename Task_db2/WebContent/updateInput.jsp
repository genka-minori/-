<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="jp.co.axiz.web.servlet.update.UpdateInputServlet" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    １箇所以上の項目を変更してください
  </p>
<c:if test="${not empty msg5}">
  <p class="error">${msg5}</p>
  </c:if>


  <form action="updateInput" method="post">
    <fieldset>
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="${sessionScope.loginIdUp}">
       <c:if test="${not empty msg1}">
        <span class="error">${msg1}</span>
        </c:if>
        <c:if test="${not empty msg6}">
        <span class="error">${msg6}</span>
        </c:if>
      </div>
      <div>
        <label>名前</label>
        <input type="text" name="userName" value="${userUp[0].user_name}">
        <c:if test="${not empty msg2}">
        <span class="error">${msg2}</span>
        </c:if>
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel" value="${userUp[0].telephone}">
        <c:if test="${not empty msg3}">
        <span class="error">${msg3}</span>
        </c:if>
      </div>
      <div>
        <label>権限</label> <select name="roleId">
          <option value="1" <c:if test="${userUp[0].role_id == 1}">selected</c:if>>${list[0].role_name}</option>
          <option value="2" <c:if test="${userUp[0].role_id == 2}">selected</c:if>>${list[1].role_name}</option>
        </select>
      </div>
      <div>
        <label>PASS</label>
        <input type="password" name="pass" value="${userUp[0].password}">
        <c:if test="${not empty msg4}">
        <span class="error">${msg4}</span>
        </c:if>
      </div>
    </fieldset>
    <div>
      <button type="submit">確認</button>
      <button type="submit"
        onclick="location.href='update.jsp'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.jsp">メニューに戻る</a>
  </div>
</body>
</html>
