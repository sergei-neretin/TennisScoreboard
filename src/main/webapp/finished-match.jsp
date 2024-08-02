<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.dto.Match"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%
Match match = (Match)request.getAttribute("match");
%>
<h1>Finished Match</h1>
<div class="table-container">
    <table>
        <tr>
            <th><%= match.getName1() %></th>
            <th>:</th>
            <th><%= match.getName2() %></th>
        </tr>
        <tr>
            <th><%= match.getSet1() %></th>
            <th>:</th>
            <th><%= match.getSet2() %></th>
        </tr>
    </table>
</div>
<%@ include file="navigation.jsp" %>
</body>
</html>