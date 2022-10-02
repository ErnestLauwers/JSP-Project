<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
    <h1>
        <span>XXX</span>
    </h1>
    <jsp:include page="header.jsp">
        <jsp:param name="hier" value="Home"/>
    </jsp:include>
    <h2>
User Overview
    </h2>

</header><main>
<table>
<tr>
<th>E-mail</th>
<th>First Name</th>
<th>Last Name</th>
</tr>
<tr>
<td>jan.janssens@hotmail.com</td><td>Jan</td><td>Janssens</td>
</tr>

<caption>Users Overview</caption>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>