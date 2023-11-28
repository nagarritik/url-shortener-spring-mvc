<%@ page import="com.ud.UrlShortenerSpringMVC.model.ShortUrl" %><%--
  Created by IntelliJ IDEA.
  User: RITIK
  Date: 28-11-2023
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ShortUrl shortUrl = (ShortUrl) request.getAttribute("shortUrl");
%>

<html>
<head>
    <title>Redirect Page</title>
</head>
<body>
<p><%=shortUrl.getLongUrl()%></p>
<a target="_blank" href=<%=shortUrl.getLongUrl()%>>
<button><%=shortUrl.getShortCode()%></button>
</a>
</body>
</html>
