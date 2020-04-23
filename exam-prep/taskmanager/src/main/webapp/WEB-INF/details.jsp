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
<h1>Task: ${task.name}</h1>
<p>Creator: ${task.creator.name}</p>
<p>Assignee:${task.assignee.name}</p>
<p>Priority:
    <c:if test="${task.priority == 0}">
        <span>Low</span>
    </c:if>
    <c:if test="${task.priority == 1}">
        <span>Medium</span>
    </c:if>
    <c:if test="${task.priority == 2}">
        <span>High</span>
    </c:if>
</p>

<c:if test="${task.creator.id.equals(userid)}">
    <a href="/tasks/${task.id}/edit">Edit</a> | <a href="/delete/${task.id}">Delete</a>
</c:if>
<c:if test="${task.assignee.id.equals(userid)}">
    <a href="/delete/${task.id}">Completed</a>
</c:if>
</body>
</html>