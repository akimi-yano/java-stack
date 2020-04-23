<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Admin Dashbaord</h1>
<a href="/logout">Logout</a>
<h2>Customers</h2>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Next Due Date</th>
            <th>Amount Due</th>
            <th>Package Type</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <c:if test="${user.getLevel().equals(9)}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.createdAt}</td>
                    <td>${user.subscription.cost}</td>
                    <td>${user.subscription.name}</td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>
<h2>Packages</h2>
<table>
    <thead>
        <tr>
            <th>Package Name</th>
            <th>Package Cost</th>
            <th>Available</th>
            <th>Users</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${packages}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.cost}</td>
                <td>${item.status}</td>
                <td>${item.subscribers.size()}</td>
                <td>
                    <c:if test="${item.status.equals(active) && item.subscribers.size() != 0}">
                        <p>deactivate | <a href="/packages/${item.id}/edit">edit</a></p>
                    </c:if>
                    <c:if test="${item.status.equals(inactive) == true}">
                        <p><a href="/activate/${item.id}">activate</a> | <a href="/packages/${item.id}/edit">edit</a></p>
                    </c:if>
                    <c:if test="${item.subscribers.size()==0 && item.status.equals(active)}">
                        <p><a href="/deactivate/${item.id}">deactivate</a> | <a href="/packages/${item.id}/edit">edit</a></p>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>Create Package</h2>
<p><form:errors path="package.*"/></p>
<form:form action="/packages" method="post" modelAttribute="package">
    <p>
        <form:label path="name">Package Name:</form:label>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="cost">Cost:</form:label>
        <form:input type="number" step="0.01" path="cost"/>
    </p>
    <input type="submit" value="Create new package">
</form:form>
</body>
</html>