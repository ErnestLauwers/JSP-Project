<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Edit</title>
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
            <jsp:param name="hier" value=""/>
        </jsp:include>
        <h2>
            Edit user
        </h2>
        <hr class="solid">
    </header>
    <main>
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <div class="invulFormulier">
            <p class="please">Please fill in all input fields.</p>
            <form method="post" action="Controller?command=Edit&userId=${requestScope.userToEdit.userid}" novalidate="novalidate">
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                                 required value="${lastNameCorrect}"> </p>
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                                   required value="${firstNameCorrect}"> </p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" required value="${emailCorrect}"></p>
                <label for="role">Role</label>
                <select name="role" id="role">
                    <option value="employee">Employee</option>
                    <option value="teamLeader">Team Leader</option>
                    <option value="director">Director</option>
                </select>
                <label for="team">Team</label>
                <select name="team" id="team">
                    <option value="alpha">Alpha</option>
                    <option value="beta">Beta</option>
                    <option value="gamma">Gamma</option>
                    <option value="delta">Delta</option>
                    <option value="epsilon">Epsilon</option>
                </select>
                <p><input type="submit" id="edit" value="Edit user"></p>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>