<%@ page import="com.ud.UrlShortenerSpringMVC.model.User" %>
<%@ page import="com.ud.UrlShortenerSpringMVC.dao.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ud.UrlShortenerSpringMVC.model.ShortUrl" %><%--
  Created by IntelliJ IDEA.
  User: RITIK
  Date: 22-08-2023
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    UserDao userDao = (UserDao) session.getAttribute("user");

    if (userDao==null){
        response.sendRedirect(request.getContextPath()+"/login");
    }

%>

<%
    List<ShortUrl> shortUrlList = (List<ShortUrl>) request.getAttribute("shortUrlList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body class="flex flex-col items-center">
<div class="py-[100px]">
    <form action="url_short_handler" method="post" class="flex flex-col gap-3 w-[250px] lg:w-[750px] lg:flex-row lg:gap-0">
        <input class="w-full border-2 px-4 py-2 rounded-full lg:border-r-0 lg:rounded-tr-none lg:rounded-br-none lg:rounded-full" type="url" name="longUrl" id="longUrl" placeholder="Enter long url" required>
        <button type="submit" class="bg-blue-600 text-white w-full rounded-full py-1 lg:w-[200px] lg:px-4 lg:rounded-tl-none lg:rounded-bl-none lg:rounded-full">
            Short
        </button>
    </form>
</div>
<div class="grid grid-cols-1 gap-3 md:grid-cols-2 lg:grid-cols-3">
    <%
        if (shortUrlList!=null){
            for (ShortUrl item:shortUrlList){
                %>
                    <div class="border px-4 py-2 w-[250px] truncate">
                        <span class="font-semibold text-blue-500"><%=item.getShortCode()%></span>
                        <p class="text-xs text-opacity-80"><%=item.getLongUrl()%></p>
                    </div>
                <%
            }
        }
    %>


</div>

<script src="https://cdn.tailwindcss.com"></script>
</body>
</html>

