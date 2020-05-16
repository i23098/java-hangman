<%@page import="ralmeida.hangman.model.HangmanGame"%>
<%@page import="ralmeida.hangman.util.StringUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
HangmanGame game = (HangmanGame) session.getAttribute("hangmanGame");

int nErrors = game.getNumErrors();
String currentGuess = StringUtil.spaceChars(game.getCurrentGuess());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hangman</title>
<script type="text/javascript">
function play(letter) {
   var form = document.forms[0];
   form.letter.value = letter;
   form.submit();
}
</script>
</head>
<body>
<h1>Hangman</h1>
<pre>
   _________
   |/      |
   |<%= nErrors == 0 ? "" : "      (_)" %>
   |<% if (nErrors > 1) { %>      <%=  nErrors < 5 ? ' ' : '\\' %>|<%= nErrors == 6 ? "/" : "" %><% } %>
   |       <%= nErrors > 1 ? '|' : ' ' %>          <%= currentGuess %>
   |<%= nErrors > 2 ? "      /" : "" %><%= nErrors > 3 ? " \\" : "" %>
   |
___|___
</pre>
<% if (game.isGameOver()) { %>
<h2>Game Over!</h2>
<h3>You <%= game.isWin() ? "Won" : "Loose" %>!!!</h3>
<div><a href="index.jsp">Play again</a></div>
<% } else { %>
<form action="game" method="POST">
    <input type="hidden" name="letter">
    <% for (int i=0; i< 26; i++) {
           char letter = (char) ('A' + i);
           if (i == 0 || i == 13) { %>
    <div>
        <% } %>
        <input type="button" value="<%= letter %>" onclick="play(this.value)"<% if (game.isRequested(letter)) { %> disabled<% } %>>
        <% if (i == 12 || i == 25) { %>
    </div>
        <% }
       } %>
</form>
<% } %>
</body>
</html>