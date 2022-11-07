<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search Result</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<div id="container">
    <header>
        <h1>
            <a href="Controller?command=Register">Register</a>
        </h1>
        <jsp:include page="header.jsp">
            <jsp:param name="hier" value=""/>
        </jsp:include>
        <h2>Search Result</h2>
        <hr class="solid">
    </header>
    <main>
        <c:choose>
            <c:when test="${found == true}">
                <p>We hebben het volgend project gevonden</p>
                <br>
                <br>
                <table>
                    <tr>
                        <th>Project Id</th>
                        <th>Name</th>
                        <th>Team</th>
                        <th>Start</th>
                        <th>End</th>
                    </tr>
                    <tr>
                        <td>${foundResult.projectId}</td>
                        <td>${foundResult.name}</td>
                        <td>${foundResult.team}</td>
                        <td>${foundResult.startDate}</td>
                        <td>${foundResult.endDate}</td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p>We hebben geen project gevonden met de opgegeven naam</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>
