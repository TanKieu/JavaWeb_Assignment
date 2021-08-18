<%-- 
    Document   : search
    Created on : Nov 3, 2020, 10:59:23 PM
    Author     : winnh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <font color="blue">Welcome, ${sessionScope.REALNAME}</font>
        <form action="LogOut">
            <input type="submit" value="Log Out" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}"></c:set>
        <form action="searchLastName">
            <br/>
            Search value <input type="text" name="txtSearchValue" value="${searchValue}" />
            <input type="submit" value="Search" />
        </form>
        <c:if test="${not empty searchValue}">
            <c:set var="errors" value="${requestScope.UPDATE_ERRORS}"></c:set>            
            <c:set var="result" value="${requestScope.SEARCHRESULT}"></c:set>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role<br/>(Admin)</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="UpdateUser" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>${dto.username}
                                    <input type="hidden" name="txtUserName" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" /><br/>
                                </td>
                                <td>
                                    <input type="text" name="txtFullName" value="${dto.fullname}" /><br/>                               
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                        <c:if test="${dto.role}">
                                            checked="checked"
                                        </c:if>
                                    />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteUser">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="lastSearch" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>                            
                        </c:forEach>
                    </tbody>
                </table>
                        <c:if test="${not empty errors}">
                            <font color="red">Update failed<font/><br/>
                                <c:if test="${not empty errors.passwordLengthError}">
                                    <font color="red">
                                        ${errors.passwordLengthError}<br/>
                                    <font/>                                        
                                </c:if> 
                                <c:if test="${not empty errors.fullNameLengthError}">
                                    <font color="red">
                                        ${errors.fullNameLengthError}<br/>
                                    <font/>                                        
                                </c:if>                                    
                        </c:if>     

            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched</h2>
            </c:if>
        </c:if>
    </body>
</html>
