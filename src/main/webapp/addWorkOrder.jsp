<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Work Order</title>
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
        <h2>Add Work Order</h2>
        <hr class="solid">
    </header>
    <main>
        <c:if test="${not empty errors}">
            <c:forEach var="error" items="${errors}">
                <div class="alert-danger">
                    <ul>
                        <li><c:out value="${error}"/></li>
                    </ul>
                </div>
            </c:forEach>
        </c:if>
        <div class="invulFormulier">
            <form method="post" action="Controller?command=AddWorkOrder" novalidate="novalidate">
                <p>
                    <label for="date">Date</label>
                    <input type="date" id="date" name="date" required value="<c:out value="${dateCorrect}"/>">
                </p>
                <p>
                    <label for="startTime">Start Time</label>
                    <input type="time" id="startTime" name="startTime" required value="<c:out value="${startTimeCorrect}"/>">
                </p>
                <p>
                    <label for="endTime">End Time</label>
                    <input type="time" id="endTime" name="endTime" required value="<c:out value="${endTimeCorrect}"/>">
                </p>
                <p>
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" required value="<c:out value="${descriptionCorrect}"/>">
                </p>
                <p><input type="submit" id="signUp" value="Add Work Order"></p>
            </form>
        </div>
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