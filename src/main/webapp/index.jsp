<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Start Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<%
String contextPath = request.getContextPath();
%>
<body>
<h1>Welcome to TennisScoreboard!</h1>
<%@ include file="navigation.jsp" %>

</body>
</html>