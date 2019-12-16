<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>extras</title>
</head>
<body>

<table>
    <c:forEach items="${extras}" var="extra">
        <tr>
            <td><c:out value="${extra.id}"/></td>
            <td> ${extra.name}</td>
            <td> ${extra.price}</td>
        </tr>
    </c:forEach>
</table>

<nav>
    <ul>
        <c:if test="${currentPage != 1}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&type=extras">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li><a>${i} <span>(current)</span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${i}&type=extras">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li><a href="coffee?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&type=extras">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

</body>
</html>