<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Edit Work Order</title>
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
            Edit Work Order
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
            <form method="post" action="Controller?command=EditWorkOrder&workOrderId=${requestScope.workOrderToEdit.workOrderId}" novalidate="novalidate">
                <p>
                    <label for="startTime">Start Time</label>
                    <input type="time" id="startTime" name="startTime" required value="${startTimeCorrect}">
                </p>
                <p>
                    <label for="endTime">End Time</label>
                    <input type="time" id="endTime" name="endTime" required value="${endTimeCorrect}">
                </p>
                <p>
                    <label for="description">Description</label>
                    <input type="text" id="description" name="description" required value="${descriptionCorrect}">
                </p>
                <p><input type="submit" id="signUp" value="Edit Work Order"></p>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>