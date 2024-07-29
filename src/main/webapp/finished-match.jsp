<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
%>
<h1>Finished Match</h1>
<table>
    <tr>
        <th><%= request.getParameter("player1")%></th>
        <th>:</th>
        <th><%= request.getParameter("player2")%></th>
    </tr>
    <tr>
        <th><%= request.getParameter("score1")%></th>
        <th>:</th>
        <th><%= request.getParameter("score2")%></th>
    </tr>
</table>
</body>
</html>