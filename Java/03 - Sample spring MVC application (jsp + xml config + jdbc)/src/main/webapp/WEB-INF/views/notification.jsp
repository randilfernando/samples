<%--
  Created by IntelliJ IDEA.
  User: randil
  Date: 3/10/18
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Greetings</title>
</head>
<body>
<h1 id="message">${message}</h1>
<script src="${contextPath}/resources/js/greet.js"></script>
<script>
    greet("message", "${message}");
</script>
</body>
</html>
