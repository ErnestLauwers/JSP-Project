<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Work Orders</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body class="workBody">

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
            <jsp:param name="hier" value="workOrders"/>
        </jsp:include>
        <h2>
            Work Order Overview
        </h2>
        <hr class="solid">
    </header>
    <main>
        <p class="descasc">Sort By Date:</p>
        <div class="sortBox"><a href="Controller?command=SortWorkOrdersAsc" class="asc" id="ascLink">Oldest First</a>
        <a href="Controller?command=SortWorkOrdersDesc" class="desc" id="descLink">Newest First</a></div>
        <c:forEach var="workOrder" items="${requestScope.workOrders}">
            <div class="grid-container">
                <div class="grid-item">
                    <p><strong>Order <strong>${workOrder.workOrderId}</strong></strong></p>
                    <p><strong>Employee: </strong>${workOrder.name} (${workOrder.team})</p>
                    <p><strong>Date: </strong>${workOrder.date}</p>
                    <p><strong>Time: </strong>from ${workOrder.startTime} to ${workOrder.endTime}</p>
                    <p><strong>Duration: </strong>${workOrder.getDurationHour()}h${workOrder.getDurationMinute()}m</p>
                    <p><strong>Description: </strong><p>${workOrder.description}</p></p>
                    <div class="wijzig2"><div class="knopWijzig2"><a class="edit" href="Controller?command=EditWorkOrderPage&workOrderId=${workOrder.workOrderId}" id="knopWijzig"><img src="images/wijzig.png" alt="wijzig"></a></div></div>
                    <div class="verwijder2"><div class="knopVerwijder2"><a class="delete" href="Controller?command=DeleteWorkOrderConf&workOrderId=${workOrder.workOrderId}" id="knopVerwijder"><img src="images/verwijder.png" alt="verwijder"></a></div></div>
                </div>
            </div>
        </c:forEach>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>