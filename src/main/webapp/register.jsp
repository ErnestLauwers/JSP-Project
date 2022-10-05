<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <a href="">Register</a>
        </h1>
        <jsp:include page="header.jsp">
            <jsp:param name="hier" value="Home"/>
        </jsp:include>
        <h2>
            Register
        </h2>
        <hr class="solid">
    </header>
    <main>
        <div class="alert-danger">
            <ul>
                <li>Some error</li>
            </ul>
        </div>
        <section class="invulFormulier">
            <p class="please">Please fill in all input fields.</p>
            <form novalidate="novalidate">
                <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
                                                             required > </p>
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                                   required value=""> </p>
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                                 required> </p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" required></p>
                <p><label for="password">Password</label><input type="password" id="password"  name="password"
                                                                required> </p>
                <p><input type="submit" id="signUp" value="Sign Up"></p>

            </form>
        </section>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>