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
<div id="container">
    <header>
        <h1>
            <a href="">Register</a>
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
            <tr>
                <td>1</td>
                <td>Jan</td>
                <td>Janssens</td>
                <td>jan.janssens@hotmail.com</td>
                <td>Alpha</td>
                <td>Employee</td>
                <td class="wijzig"><div class="knopWijzig"><a href=""><img src="images/wijzig.png" alt="wijzig"></a></div></td>
                <td class="verwijder"><div class="knopVerwijder"><a href=""><img src="images/verwijder.png" alt="verwijder"></a></div></td>
            </tr>
        </table>
    </main>
    <footer>
        <p>&copy; Webontwikkeling 3, UC Leuven-Limburg</p>
    </footer>
</div>
</body>
</html>