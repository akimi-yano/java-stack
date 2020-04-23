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
<h1>Welcome, ${me.name}</h1>
<a href="/logout">Logout</a>
<a href="/high">Priority High - Low</a> | <a href="/low">Priority Low-High</a>
<table>
    <thead>
        <tr>
            <th>Task</th>
            <th>Creator</th>
            <th>Assignee</th>
            <th>Priority</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${tasks}" var="item">
        <tr>
            <td>
                <a href="/tasks/${item.id}">${item.name}</a>
            </td>
            <td>${item.creator.name}</td>
            <td>${item.assignee.name}</td>
            <td>
                <c:if test="${item.priority == 0}">
                    <p>Low</p>
                </c:if>
                <c:if test="${item.priority == 1}">
                    <p>Medium</p>
                </c:if>
                <c:if test="${item.priority == 2}">
                    <p>High</p>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="/tasks/new">Create Task</a>
</body>
</html>