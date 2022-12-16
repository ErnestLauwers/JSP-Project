<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Delete Project</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<c:if test="${roleLoggedIn.getStringValue() == 'director'}">
<div id="container">
    <header>
        <h1>
            <a href="Controller?command=Register">Register</a>
        </h1>
        <jsp:include page="header.jsp">
            <jsp:param name="hier" value=""/>
        </jsp:include>
        <h2>
            Delete Project
        </h2>
        <hr class="solid">
    </header>
    <main>
        <h3>Are you sure you want to delete this project?</h3>
        <div class="inhoudVerwijder">
            <p>Project name: ${requestScope.projectToDelete.name}</p>
            <p>Startdate: ${requestScope.projectToDelete.startDate} Enddate: ${requestScope.projectToDelete.endDate}</p>
            <p>Project Id: #${requestScope.projectToDelete.projectId}</p>
        </div>
        <div class="invulFormulierVerwijder">
            <form action="Controller?command=DeleteProject&projectid=${requestScope.projectToDelete.projectId}" method="post" novalidate>
                <div class="invulVeldGroen">
                    <input type="submit" name="Groen" value="Yes" id="submitYes">
                </div>
            </form>
            <form action="Controller?command=Projects" method="post" novalidate>
                <div class="invulVeldRood">
                    <input type="submit" name="Rood" value="No" id="submitNo">
                </div>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</c:if>
<c:if test="${userLoggedIn == null || roleLoggedIn.getStringValue() == 'employee' || roleLoggedIn.getStringValue() == 'teamleader'}">
    <div class="alert-danger">
        <ul>
            <li>You Do Not Have Access to This Page!</li>
        </ul>
    </div>
</c:if>
</body>
</html>
