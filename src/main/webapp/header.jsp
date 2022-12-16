<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <p class="logo"><a href="Controller?command=Home">W</a></p>
    <ul>
        <li ${param.hier eq 'Home'?"class = hier":""}><a href="Controller?command=Home">Home</a></li>
        <li ${param.hier eq 'Users'?"class = hier":""}><a href="Controller?command=Overview" id ="overviewNav">Users</a></li>
        <c:if test="${userLoggedIn != null}">
            <li ${param.hier eq 'WorkOrders'?"class = hier":""}><a href="Controller?command=WorkOrders" id ="workOrdersNav">Work Orders</a></li>
        </c:if>
        <c:if test="${userLoggedIn != null}">
            <li ${param.hier eq 'AddWorkOrder'?"class = hier":""}><a href="Controller?command=AddWorkOrderPage">Add Work Order</a></li>
        </c:if>
        <c:if test="${userLoggedIn != null}">
            <li ${param.hier eq 'Projects'?"class = hier":""}><a href="Controller?command=Projects" id="projectsNav">Projects</a></li>
        </c:if>
        <c:if test="${userLoggedIn != null && roleLoggedIn.getStringValue() == 'teamleader' || roleLoggedIn.getStringValue() == 'director'}">
            <li ${param.hier eq 'AddProject'?"class = hier":""}><a href="Controller?command=AddProjectPage">Add Project</a></li>
        </c:if>
        <c:if test="${userLoggedIn != null && roleLoggedIn.getStringValue() == 'director'}">
            <li ${param.hier eq 'SearchWorkOrder'?"class = hier":""}><a href="Controller?command=SearchWorkOrderPage">Search Work Order</a></li>
        </c:if>
        <c:if test="${userLoggedIn != null && roleLoggedIn.getStringValue() == 'director'}">
            <li ${param.hier eq 'SearchProject'?"class = hier":""}><a href="Controller?command=SearchProjectPage">Search Project</a></li>
        </c:if>
    </ul>
</nav>
