<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 2019-01-13
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I'm title</title>
</head>
<body>
    <a href="handler/welcome">Welcome!</a>
    <br>---testModelAndView--- <br>
    <form action="handler/student" method="post">
        id:<input name="id" >
        name:<input name="name" >
        <input type="submit" value="post">
    </form>
    <br>---testModelMap--- <br>
    <form action="handler/testModelMap" method="post">
        id:<input name="id" >
        name:<input name="name" >
        <input type="submit" value="post">
    </form>
    <br>---testMap--- <br>
    <form action="handler/testMap" method="post">
        id:<input name="id" >
        name:<input name="name" >
        <input type="submit" value="post">
    </form>
    <br>---testModel--- <br>
    <form action="handler/testModel" method="post">
        id:<input name="id" >
        name:<input name="name" >
        <input type="submit" value="post">
    </form>
</body>
</html>
