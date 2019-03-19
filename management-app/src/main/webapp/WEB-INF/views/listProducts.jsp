<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
<div>
    <div>
        <h1>SKT Task</h1>

        <h3>List of products</h3>

        <c:forEach var="product" items="${products}">

            <table>
                <tr class="tr1">
                    <td>Name</td>
                    <td>${product.name}</td>
                </tr>
            </table>
        </c:forEach>
    </div>
</div>
</body>
</html>