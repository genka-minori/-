<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>これでよろしいですか？</p>

    <c:if test="${not empty msg}">
  <p class="error">${msg}</p>
	</c:if>
  <form action="updateConfirm" method="post">
    <fieldset class="label-130">
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="${sessionScope.loginIdUp}" readonly>
      </div>
      <div>
        <label>名前</label>
        <input type="text" name="userName" value="${sessionScope.nameUp}" readonly>
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel" value="${sessionScope.telUp}" readonly>
      </div>
      <div>
        <label>権限</label>
        <input type="text" name="roleName" value="${sessionScope.roleNameUp}" readonly>
      </div>
      <div>
        <label>PASS（再入力）</label>
        <input type="password" name="rePass" <c:if test = "${not empty passRe}">value="${sessionScope.passRe}" readonly</c:if>>
      </div>
    </fieldset>
    <div>
      <button type="submit">更新</button>
      <button type="submit"
        onclick="location.href='updateInput.jsp'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.jsp">メニューに戻る</a>
  </div>
</body>
</html>
