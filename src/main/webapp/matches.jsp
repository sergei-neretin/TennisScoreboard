<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.service.FindingMatchesByPlayerNameService" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
FindingMatchesByPlayerNameService findingMatchesByPlayerNameService = FindingMatchesByPlayerNameService.getInstance();
String name = request.getParameter("filter_by_player_name");
%>
<h1>Matches</h1>
<form class="matchesSearch" action="/matches" method="get">
    <label for="filter_by_player_name">Player Name:</label>
    <input type="text" id="filter_by_player_name" name="filter_by_player_name" placeholder="John Doe" required>
    <input type="submit" value="Find">
</form>
<h2>
    <%= findingMatchesByPlayerNameService.find(name) %>
</h2>
</body>
</html>