<%--
  Created by IntelliJ IDEA.
  User: randil
  Date: 3/12/18
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/users/register" method="post">
        Username: <input title="username" type="text" name="username"><br>
        Password: <input title="password" type="password" name="password"><br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
