<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.dto.Match"%>
<%@page import="com.sergeineretin.tennisScoreboard.Points"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%
    String uuidString = request.getParameter("uuid");
    Match match = (Match)request.getAttribute("match");
    String contextPath = request.getContextPath();
    %>
    <h1>Match Score</h1>
    <div class="table-container">
        <table>
            <tr>
                <th><%= match.getName1()%></th>
                <th></th>
                <th><%= match.getName2()%></th>
            </tr>
            <tr>
                <th><%= match.getSet1()%></th>
                <th>:</th>
                <th><%= match.getSet2()%></th>
            </tr>
            <tr>
                <th><%=match.getGame1()%></th>
                <th>:</th>
                <th><%=match.getGame2()%></th>
            </tr>
            <tr>
                <th><%
                    if(match.getPoints1() != Points.ADVANTAGE) {
                    out.println(match.getPoints1());
                    } else {
                    out.println("ADVANTAGE");
                    }
                    %>
                </th>
                <th>
                    <%
                    if(match.getGame1() == 6 && match.getGame2() == 6) {
                    out.println("TIE BREAK");
                    } else {
                    out.println(":");
                    }
                    %>
                </th>
                <th>
                    <%
                    if(match.getPoints2() != Points.ADVANTAGE) {
                    out.println(match.getPoints2());
                    } else {
                    out.println("ADVANTAGE");
                    }
                    %>
                </th>
            </tr>
            <tr>
                <th>
                    <form action="<%= contextPath %>/match-score" method="post">
                        <input type="hidden" name="uuid" value="<%= uuidString %>">
                        <button type="submit" name="playerName" value="<%= match.getName1()%>"> player 1 wins the current point</button>
                    </form>
                </th>
                <th></th>
                <th>
                    <form action="<%= contextPath %>/match-score" method="post">
                        <input type="hidden" name="uuid" value="<%= uuidString %>">
                        <button type="submit" name="playerName" value="<%= match.getName2()%>"> player 2 wins the current point</button>
                    </form>
                </th>
            </tr>
        </table>
    </div>
</body>
</html>