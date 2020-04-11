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
    <c:when test="${listAllBooks.size()>0}">
        Available books are: <br /><br />
        <table border="1">
            <thead>
            <td>Name</td>
            <td>Quantity</td>
            <td></td>
            </thead>

        <c:forEach items="${listAllBooks}" var="listItem">
                    <tr>
                        <td>${listItem.name} <br /></td>
                        <td>${listItem.quantity}</td>
                        <td><a href="/borrow?id=${listItem.id}">Borrow</a> </td>
                    </tr>
        </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        There is no book in Library.
    </c:otherwise>
</c:choose>

<br><br>
My Borrowed List<br>

<c:choose>
<c:when test="${listUserBooks.size()>0}">

    <c:choose>
        <c:when test="${status==1}">
            <span style="color: green; "> Book Successfully Added to List.</span>
        </c:when>
        <c:when test="${status==0}">
            <span style="color: red; "> Borrowing limit is over.</span>
        </c:when>
        <c:when test="${status==2}">
            <span style="color: red; "> Book Already borrowed.</span>
        </c:when>
    </c:choose>
    <br>
<table border="1">
    <thead>
    <td>Name</td>
    <td></td>
    </thead>
    <c:forEach items="${listUserBooks}" var="listItem">
    <tr>
        <td>${listItem.bookname}</td>
        <td><a href="/borrow?id=${listItem.id}">Return</a> </td>
    </tr>
    </c:forEach>
</table>
</c:when>
    <c:otherwise>
<table border="1"><tr><td>
        There are no books in your cart.
</td></tr>
</table>
    </c:otherwise>
</c:choose>

</body>
</html>