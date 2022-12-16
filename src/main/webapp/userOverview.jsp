<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta charset="UTF-8">
    <title>Users</title>
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
            <jsp:param name="hier" value="Overview"/>
        </jsp:include>
        <h2>
            Overview
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
                <c:if test="${userLoggedIn != null}">
                    <th>Edit</th>
                </c:if>
                <c:if test="${roleLoggedIn.getStringValue() == 'director'}">
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.userid}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.team}</td>
                    <td>${user.role}</td>
                    <c:if test="${userLoggedIn != null && roleLoggedIn.getStringValue() == 'employee' && idLoggedIn == user.userid || roleLoggedIn.getStringValue() == 'teamleader' && teamLoggedIn == user.team || roleLoggedIn.getStringValue() == 'director'}">
                        <td class="wijzig"><div class="knopWijzig"><a class="edit" href="Controller?command=StartEdit&id=${user.userid}" id="knopWijzig"><img src="images/wijzig.png" alt="wijzig"></a></div></td>
                    </c:if>
                    <c:if test="${roleLoggedIn.getStringValue() == 'director'}">
                        <td class="verwijder"><div class="knopVerwijder"><a class="delete" href="Controller?command=DeleteConfirmation&id=${user.userid}" id="knopVerwijder"><img src="images/verwijder.png" alt="verwijder"></a></div></td>
                    </c:if>
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