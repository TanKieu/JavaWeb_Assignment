<%-- 
    Document   : createNewAccount
    Created on : Nov 5, 2020, 12:52:16 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createNewAccount" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"></c:set>
            <c:set var="name" value="${param.txtUsername}"></c:set>
            Username:  <input type="text" name="txtUsername" value="${name}" /> "(6-20 chars)"
            <br/> 
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red"> 
                    ${errors.usernameLengthError}<br/>
                </font>
            </c:if>
            Password:  <input type="password" name="txtPassword" value="" /> "(6-20 chars)"
            <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}<br/>
                </font>
            </c:if>
            Confirm:   <input type="password" name="txtConfirm" value="" />
            <br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}<br/>
                </font>
            </c:if>
            <c:set var="fullname" value="${param.txtFullname}"></c:set>                
            Full name: <input type="text" name="txtFullname" value="${fullname}" /> "(2-50 chars)"
            <br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                    ${errors.fullNameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create" />
            <input type="reset" value="Reset" />
        </form>
            <c:if test="${not empty errors.usernameIsExist}">
                <font color="red"> 
                    ${errors.usernameIsExist}
                </font>
            </c:if>
    </body>
</html>
