<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.sergeineretin.tennisScoreboard.service.FindingMatchesByPlayerNameService" %>
<%@page import="com.sergeineretin.tennisScoreboard.dto.MatchDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sergeineretin.tennisScoreboard.Utils" %>

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
String pageString = request.getParameter("page");
int pageNumber = 1;
if(pageString != null && !pageString.isEmpty()) {
    pageNumber = Integer.parseInt(pageString);
}
List<MatchDto> matches = findingMatchesByPlayerNameService.find(name, pageNumber);
long numberOfPages = findingMatchesByPlayerNameService.getNumberOfPages(name);
%>
<h1>Matches</h1>
<form class="matchesSearch" action="/matches" method="get">
   <div class="form-container">
       <div class="form-group"><label for="filter_by_player_name">Player Name:</label></div>
       <div class="form-group"><input type="text" id="filter_by_player_name" name="filter_by_player_name" placeholder="John Doe" ></div>
       <div class="form-group"><input type="submit" value="Find"></div>

       <table>
           <tr>
               <th>Player 1</th>
               <th>Player 2</th>
               <th>Winner</th>
           </tr>
           <%
           for(MatchDto match : matches) {
           %>
           <tr>
               <th><%= match.getPlayer1().getName()%></th>
               <th><%= match.getPlayer2().getName()%></th>
               <th><%= match.getWinner().getName()%></th>
           </tr>
           <%
           }
           %>
       </table>
       <div class="pages-container">
           <button type="submit" class="page" name="page" value="1"> 1</button>
           <%
           for(int i = 2; i <= numberOfPages; i++) {
           %>
           <button type="submit" class="page" name="page" value="<%= i%>"> <%= i%></button>
           <%
           }
           %>
       </div>
   </div>
</form>
</body>
</html>