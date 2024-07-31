<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.dto.MatchScoreDto"%>
<%@page import="com.sergeineretin.tennisScoreboard.dto.PlayerDto" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
MatchScoreDto matchScoreDto = (MatchScoreDto)request.getAttribute("match");
%>
<h1>Finished Match</h1>
<div class="table-container">
    <table>
        <tr>
            <th><%= matchScoreDto.getPlayer1().getName() %></th>
            <th>:</th>
            <th><%= matchScoreDto.getPlayer2().getName() %></th>
        </tr>
        <tr>
            <th><%= matchScoreDto.getScore1()%></th>
            <th>:</th>
            <th><%= matchScoreDto.getScore2()%></th>
        </tr>
    </table>
</div>

</body>
</html>