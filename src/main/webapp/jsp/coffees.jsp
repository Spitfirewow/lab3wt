<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>coffees</title>
</head>
<body>

<table>
    <c:forEach items="${coffees}" var="coffee">
        <tr>
            <td><c:out value="${coffee.id}"/></td>
            <td> ${coffee.name}</td>
            <td> ${coffee.weightL}</td>
            <td> ${coffee.weightM}</td>
            <td> ${coffee.weightS}</td>
        </tr>
    </c:forEach>
</table>

<nav>
    <ul>
        <c:if test="${currentPage != 1}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&type=coffees">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li><a>${i} <span>(current)</span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${i}&type=coffees">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&type=coffees">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

</body>
</html>