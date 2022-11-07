<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Delete Work Order</title>
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
            Delete Work Order
        </h2>
        <hr class="solid">
    </header>
    <main>
        <h3>Are you sure you want to delete this Work Order?</h3>
        <div class="inhoudVerwijder">
            <p>${requestScope.workOrderToDelete.date} st: ${requestScope.workOrderToDelete.startTime} et: ${requestScope.workOrderToDelete.endTime}</p>
            <p>Id: #${requestScope.workOrderToDelete.workOrderId}</p>

        </div>
        <div class="invulFormulierVerwijder">
            <form action="Controller?command=DeleteWorkOrder&workOrderId=${requestScope.workOrderToDelete.workOrderId}" method="post" novalidate>
                <div class="invulVeldGroen">
                    <input type="submit" name="Groen" value="Yes" id="submitYes">
                </div>
            </form>
            <form action="Controller?command=WorkOrders" method="post" novalidate>
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
</body>
</html>