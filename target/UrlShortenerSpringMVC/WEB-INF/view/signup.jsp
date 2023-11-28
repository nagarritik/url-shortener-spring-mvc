<%@ page import="com.ud.UrlShortenerSpringMVC.dao.UserDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup</title>
</head>
<body class="flex justify-center items-center h-screen">
<div class="flex flex-col justify-center items-center gap-5">
    <span class="text-blue-600 text-2xl font-semibold">Register an account</span>
    <form action="signup_handler" method="post" class="flex flex-col justify-center items-center w-[250px] md:w-full lg:w-[500px] gap-3">
        <div>
            <input class="w-full border-2 px-4 py-1 border-b-0 rounded-tl-lg rounded-tr-lg" type="text" name="name" id="name" placeholder="Enter name" required>
            <input class="w-full border-2 px-4 py-1 border-b-0" type="text" name="username" id="username" placeholder="Enter username" required>
            <input class="w-full border-2 px-4 py-1 border-b-0" type="email" name="email" id="email" placeholder="Enter email" required>
            <input class="w-full border-2 px-4 py-1 rounded-bl-lg rounded-br-lg" type="password" name="password" id="password" placeholder="Enter password" required>
        </div>
        <button type="submit" class="bg-blue-600 text-white w-full rounded-lg py-1">Signup</button>
    </form>
    <span>Account already exist? <a class="text-blue-600 text-opacity-80" href="login">Login</a></span>
</div>
<script src="https://cdn.tailwindcss.com"></script>
</body>
</html>

<%

    UserDao userDao = (UserDao) session.getAttribute("user");

    if (userDao!=null){
        response.sendRedirect(request.getContextPath()+"/dashboard");
    }

%>