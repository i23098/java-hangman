<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hangman</title>
<style type="text/css">
/* using class so that browser doesn't try to save "password"... it's not a login! */
input.password {
    text-security:disc;
    -webkit-text-security:disc;
    -mox-text-security:disc;
}
</style>
</head>
<body>
<h1>Hangman</h1>
<form action="game" method="POST">
Please insert word: <input type="text" name="word" class="password">
<input type="submit" value="Play!">
</form>
</body>
</html>