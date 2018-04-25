<%--
  Created by IntelliJ IDEA.
  User: randil
  Date: 3/12/18
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/users/login" method="post">
        Username: <input type="text" title="username" name="username"><br>
        Password: <input type="password" title="password" name="password"><br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
