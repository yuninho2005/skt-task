<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
<div>
    <div>
        <h1>SKT Task</h1>

        <h3>List of products</h3>



            <table>
                <tr class="tr1">
                    <th>Number</th>
                    <th>Name</th>
                </tr>

                <c:forEach var="product" items="${products}" varStatus="loop">
                <tr>
                    <td>${loop.index}</td>
                    <td>${product.name}</td>
                </tr>
                </c:forEach>
            </table>

    </div>
</div>
</body>
</html>