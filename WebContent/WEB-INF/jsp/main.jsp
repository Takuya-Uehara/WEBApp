<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("account");
// アプリケーションスコープに保存されたつぶやきリストを取得
List<Mutter> mutterList =
(List<Mutter>) request.getAttribute("mutterList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
<h1>メインユーザーページ</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="/Web/Logout">ログアウト</a>
</p>
<p><a href="/Web/MutterMain">更新</a></p>
<form action="/Web/MutterMain" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<% if(errorMsg != null){ %>
<p><%= errorMsg %></p>
<% } %>
<% for(Mutter mutter : mutterList){%>
<p><%=mutter.getUserName()%>：<%=mutter.getText()%>
<%
if (mutter.getUserName() == loginUser.getName()){
%>
<a href="">削除</a>
<% } %>
</p>
<% } %>
</body>
</html>