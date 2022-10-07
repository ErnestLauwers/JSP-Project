<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
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
            <jsp:param name="hier" value="Overview"/>
        </jsp:include>
        <h2>
            User Overview
        </h2>
        <hr class="solid">
    </header>
    <main>
        <table>
            <tr>
                <th>UserId</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>E-mail</th>
                <th>Team</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.userid}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.team}</td>
                    <td>${user.role}</td>
                    <td class="wijzig"><div class="knopWijzig"><a href="Controller?command=StartEdit&id=${user.userid}"><img src="images/wijzig.png" alt="wijzig"></a></div></td>
                    <td class="verwijder"><div class="knopVerwijder"><a href="Controller?command=DeleteConfirmation&id=${user.userid}"><img src="images/verwijder.png" alt="verwijder"></a></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>