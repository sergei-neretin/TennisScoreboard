<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.dto.Match"%>
<%@page import="com.sergeineretin.tennisScoreboard.service.OngoingMatchesService"%>
<%@page import="java.util.UUID"%>
<%@page import="com.sergeineretin.tennisScoreboard.service.PlayerService"%>
<%@page import="com.sergeineretin.tennisScoreboard.Points"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <%
    OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    PlayerService playerService = PlayerService.getInstance();
    String uuidString = request.getParameter("uuid");
    Match match = ongoingMatchesService.getMatch(uuidString);
    String name1 = playerService.getPlayer(match.getId1()).getName();
    String name2 = playerService.getPlayer(match.getId2()).getName();
    %>
    <h1>Match Score</h1>
    <table>
        <tr>
            <th><%= name1 %></th>
            <th></th>
            <th><%= name2 %></th>
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
                <form action="/match-score" method="post">
                    <input type="hidden" name="uuid" value="<%= uuidString %>">
                    <button type="submit" name="playerId" value="<%= match.getId1()%>"> player 1 wins the current point</button>
                </form>
            </th>
            <th></th>
            <th>
                <form action="/match-score" method="post">
                    <input type="hidden" name="uuid" value="<%= uuidString %>">
                    <button type="submit" name="playerId" value="<%= match.getId2()%>"> player 2 wins the current point</button>
                </form>
            </th>
        </tr>
    </table>
</body>
</html>