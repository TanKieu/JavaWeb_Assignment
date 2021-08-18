<%-- 
    Document   : viewCart
    Created on : Nov 5, 2020, 12:48:38 AM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="Cart" value="${sessionScope.CUSTCART}"></c:set>
        <c:if test="${not empty Cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <form action="removeBook">
                    <c:forEach var="items" items="${Cart.item}" varStatus="counter">
                        <c:set var="bookID" value="${Cart.bookID}"></c:set>
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${items.key}
                            </td>
                            <td>
                                ${items.value} 
                            </td>
                            <td>
                                <input type="checkbox" name="chBook" value="${items.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="shoppingPage">Add More Book To Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Books" />
                            </td>
                    </form>
                <form action="checkout">
                    <c:set var="error" value="${requestScope.CHECKOUT_ERRORS}"></c:set>
                    Client Name <input type="text" name="txtClientName" value="" />
                        <input type="submit" value="Check Out" /><br/>
                    <c:if test="${not empty error.clientnameLengthError}">
                        <font color="red">${error.clientnameLengthError}<font/><br/>
                    </c:if>
                </form>                        
                        </tr>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty Cart}">
            <h3>No cart is available</h3>
            <a href="shoppingPage">Continue Shopping</a>
        </c:if>
    </body>
</html>
