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
<body style="text-align: center">
<h2>Welcome to My Library.</h2><br>

<c:choose>
    <c:when test="${listAllBooks.size()>0}">
        Available books are: <br /><br />
        <table border="1" style="margin-left: auto;margin-right: auto">
            <thead>
            <td style="color: red">Name</td>
            <td style="color: red">Quantity</td>
            <td></td>
            </thead>

        <c:forEach items="${listAllBooks}" var="listItem">
                    <tr>
                        <td>${listItem.name} <br /></td>
                        <td>${listItem.quantity}</td>
                        <td><a href="/borrow?id=${listItem.id}" style="color: green">Borrow</a> </td>
                    </tr>
        </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        There is no book in Library.
    </c:otherwise>
</c:choose>

<br><br><br>
My Borrowed List<br><br>

<c:choose>
<c:when test="${listUserBooks.size()>0}">

<table border="1" style="margin-left: auto;margin-right: auto">
    <thead>
    <td>Name</td>
    <td></td>
    </thead>
    <c:forEach items="${listUserBooks}" var="listItem">
    <tr>
        <td style="color: green">${listItem.bookname}</td>
        <td><a style="color: red" href="/return?bookname=${listItem.bookname}">Return</a> </td>
    </tr>
    </c:forEach>


</table>

    <br>
    <c:choose>
        <c:when test="${status==0}">
            <span style="color: red; "> Borrowing limit is over.</span>
        </c:when>
        <c:when test="${status==1}">
            <span style="color: green; "> Book Successfully Added to List.</span>
        </c:when>
        <c:when test="${status==2}">
            <span style="color: red; "> Book Already borrowed.</span>
        </c:when>
        <c:when test="${status==3}">
            <span style="color: green; ">Book Returned</span>
        </c:when>
        <c:otherwise>
            <span style="color: red; "> </span>
        </c:otherwise>
    </c:choose>

</c:when>
    <c:otherwise>
<table border="1" style="margin-left: auto;margin-right: auto"><tr><td>
        There are no books in your cart.
</td></tr>
</table>
    </c:otherwise>
</c:choose>

</body>
</html>