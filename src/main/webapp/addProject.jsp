<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Project</title>
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
        <h2>Add Project</h2>
        <hr class="solid">
    </header>
    <main>
        <div class="invulFormulier">
            <form method="post" action="Controller?command=AddProject" novalidate="novalidate">
                <p>
                    <label for="projectName">Project Name <sup>*</sup></label>
                    <input type="text" id="projectName" name="projectName" required>
                </p>
                <p>
                    <label for="startDate">Start Date <sup>*</sup></label>
                    <input type="date" id="startDate" name="startDate" required>
                </p>
                <p>
                    <label for="endDate">End Date</label>
                    <input type="date" id="endDate" name="endDate">
                </p>
                <p><input type="submit" id="signUp" value="Add Project"></p>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>
