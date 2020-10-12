<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<% List<String> words = (List<String>) request.getAttribute("words"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hangman - Admin</title>
</head>
<body>
<h1>Words</h1>
<form method="POST">
Word: <input name="word" type="text">
<input type="submit" value="Add">
</form>
<ul>
<% for (String word : words) { %>
<li><%= word %></li>
<% } %>
</ul>
</body>
</html>