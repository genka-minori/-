<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>これでよろしいですか？</p>

  <form action="deleteConfirm" method="post">
    <fieldset>
      <div>
        <label>ID</label>
        <input type="text" name="loginId" value="${userDe[0].login_id}" readonly>
      </div>
      <div>
        <label>名前</label>
        <input type="text" name="userName" value="${userDe[0].user_name}" readonly>
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="tel" value="${userDe[0].telephone}" readonly>
      </div>
      <div>
        <label>権限</label>
        <input type="text" name="roleName" value="${sessionScope.roleNameDe}" readonly>
      </div>
      <input type="hidden" name="userId" value="${userDe[0].user_id}" />
    </fieldset>
    <div>
      <button type="submit">削除</button>
      <button type="submit"
        onclick="location.href='delete.jsp'; return false;">戻る</button>
    </div>
  </form>
  <div>
    <a href="menu.jsp">メニューに戻る</a>
  </div>
</body>
</html>
