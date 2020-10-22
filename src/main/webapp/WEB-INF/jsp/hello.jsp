<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>System Cars</title>
</head>
<body>
<div>
    <h1>System Cars</h1>
</div>
<div>
    <table>
        <tr>
            <td>Car Id</td>
            <td>Car Model</td>
            <td>Car Creation_year</td>
            <td>Car User_id</td>
            <td>Car Price</td>
            <td>Car Color</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="cars" items="${cars}">
            <tr>
                <td>${cars.id}</td>
                <td>${cars.model}</td>
                <td>${cars.creation_year}</td>
                <td>${cars.user_id}</td>
                <td>${cars.price}</td>
                <td>${cars.color}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    ${singleCar}
</div>
</body>
</html>
