<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
 
<body>
<form action="/Web/RegisterUser" method="post">
ログインID：<input type="text" name="id">
パスワード：<input type="text" name="pass"><br>
名前：<input type="text" name="name"><br>
<br>
<input type="submit" value="確認">
</form>
 
</body>
</html>