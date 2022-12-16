<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Projects</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<c:if test="${roleLoggedIn.getStringValue() == 'teamleader' || roleLoggedIn.getStringValue() == 'director' || roleLoggedIn.getStringValue() == 'employee'}">
<div id="container">
    <header>
        <h1>
            <a href="Controller?command=Register">Register</a>
        </h1>
        <jsp:include page="header.jsp">
            <jsp:param name="hier" value=""/>
        </jsp:include>
        <h2>Projects</h2>
        <hr class="solid">
    </header>
    <main>
        <p class="descasc">Sort By Start Time:</p>
        <div class="sortBox"><a href="Controller?command=SortProjectsAsc" class="asc" id="ascLink">Closest</a>
            <a href="Controller?command=SortProjectsDesc" class="desc" id="descLink">Furthest</a></div>
        <table>
            <tr>
                <th>Project Id</th>
                <th>Name</th>
                <th>Team</th>
                <th>Start</th>
                <th>End</th>
                <c:if test="${roleLoggedIn.getStringValue() == 'director'}">
                    <th>Edit</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach var="project" items="${requestScope.projects}">
                <c:if test="${teamLoggedIn == project.team || roleLoggedIn.getStringValue() == 'teamleader' || roleLoggedIn.getStringValue() == 'director'}">
                <tr>
                    <td>${project.projectId}</td>
                    <td>${project.name}</td>
                    <td>${project.team}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <c:if test="${roleLoggedIn.getStringValue() == 'director'}">
                        <td class="wijzig"><div class="knopWijzig"><a class="edit" href="Controller?command=EditProjectPage&projectid=${project.projectId}" id="knopWijzig"><img src="images/wijzig.png" alt="wijzig"></a></div></td>
                        <td class="verwijder"><div class="knopVerwijder"><a class="delete" href="Controller?command=DeleteProjectConfirmation&projectId=${project.projectId}" id="knopVerwijder"><img src="images/verwijder.png" alt="verwijder"></a></div></td>
                    </c:if>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</c:if>
<c:if test="${userLoggedIn == null}">
    <div class="alert-danger">
        <ul>
            <li>You Do Not Have Access to This Page!</li>
        </ul>
    </div>
</c:if>
</body>
</html>
