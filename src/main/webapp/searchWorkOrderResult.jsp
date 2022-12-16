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

<c:if test="${roleLoggedIn.getStringValue() == 'director'}">
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
                    <p>We found the following work orders</p>
                    <br>
                    <br>
                    <table>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Team</th>
                            <th>Date</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                        </tr>
                        <tr>
                            <td>${foundResult.workOrderId}</td>
                            <td>${foundResult.name}</td>
                            <td>${foundResult.team}</td>
                            <td>${foundResult.date}</td>
                            <td>${foundResult.startTime}</td>
                            <td>${foundResult.endTime}</td>
                        </tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>We hebben geen work order gevonden met het opgegeven team</p>
                </c:otherwise>
            </c:choose>
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