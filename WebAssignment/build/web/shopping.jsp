<%-- 
    Document   : shopping
    Created on : Nov 4, 2020, 4:52:41 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Book Shopping Online</h1>
        <c:set var="bookList" value="${sessionScope.BOOKLIST}"></c:set>
        <c:if test="${not empty bookList}">
            <form action="cart">
            "Choose Book"<br/>
            <select name="cboBook">
                    <c:forEach var="book" items="${bookList}">
                    <option>${book.getBookname()}</option>    
                    </c:forEach>
                </select>
            <input type="submit" value="Add Book to Your Cart" />
            </form>
            <form action="view">
            <input type="submit" value="View Your Cart" />
            </form>
        </c:if>
        <c:if test="${empty bookList}">
            <h1>No book available</h1>
        </c:if>
        <a href="loginPage">Back to Login</a>
    </body>
</html>
