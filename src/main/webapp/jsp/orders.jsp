<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>orders</title>
</head>
<body>

<table>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td> ${order.dateTime}</td>
            <td> ${order.ready}</td>
            <td> ${order.userId}</td>
        </tr>
    </c:forEach>
</table>

<nav>
    <ul>
        <c:if test="${currentPage != 1}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&type=orders">Previous</a></li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li><a>${i} <span>(current)</span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${i}&type=orders">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&type=orders">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

</body>
</html>