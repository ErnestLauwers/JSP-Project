<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <a href="Controller?command=Register">Register</a>
        </h1>
        <jsp:include page="header.jsp">
            <jsp:param name="hier" value="Home"/>
        </jsp:include>
        <h2>Log In</h2>
        <hr class="solid">
    </header>
    <main>
        <div>
            <c:if test="${error != null}">
                <p class="alert-danger-login">${error}</p>
            </c:if>
            <form method="post" action="Controller?command=Login">
                <div>
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="E-mail">
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Password">
                </div>
                <div>
                    <input id="login-button" type="submit" value="Login">
                </div>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>