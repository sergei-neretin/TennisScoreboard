<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Match</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Create a New Match</h1>
<form action="new_match" method="post">
    <label for="player1">Name of 1st player:</label><br>
    <input type="text" id="player1" name="player1" placeholder="John Doe" required><br>
    <label for="player2">Name of 2nd player:</label><br>
    <input type="text" id="player2" name="player2" placeholder="Jane Doe" required><br>
    <input class="start" type="submit" value="Start">
</form>
</body>
</html>