<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search Work Order</title>
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
            <h2>Search Work Order</h2>
            <hr class="solid">
        </header>
        <main>
            <div class="invulFormulier">
                <form method="post" action="Controller?command=SearchWorkOrder" novalidate="novalidate">
                    <c:if test="${errorAanwezig == true}">
                        <div class="alert-danger">
                            <ul>
                                <li>${error}</li>
                            </ul>
                        </div>
                    </c:if>
                    <p>
                        <label for="search">Naar welke work order bent u opzoek?</label>
                        <input type="text" id="search" name="search" required>
                    </p>
                    <p><input type="submit" id="signUp" value="Search"></p>
                </form>
            </div>
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