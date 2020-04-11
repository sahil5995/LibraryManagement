<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>First Web Application</title>
</head>
<body>
<h3>Welcome to My Library.</h3><br>

<c:choose>
    <c:when test="${list.size()>0}">
        Available books are: <br /><br />
        <table border="1">
            <thead>
            <td>Name</td>
            <td>Quantity</td>
            </thead>

        <c:forEach items="${list}" var="listItem">
                    <tr>
                        <td>${listItem.name} <br /></td>
                        <td>${listItem.quantity}</td>
                    </tr>
        </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        There is no book in Library.
    </c:otherwise>
</c:choose>






</body>
</html>