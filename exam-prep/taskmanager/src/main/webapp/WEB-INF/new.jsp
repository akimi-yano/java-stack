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
<h1>New Task</h1>
<p>
    <form:errors path="task.*" />
</p>
<p>${sizeError}</p>
<form:form action="/tasks/new" method="post" modelAttribute="task">
    <p>
        <form:label path="name">Task Name</form:label>
        <form:input path="name" />
    </p>
            <form:label path="assignee">Assignee</form:label>
    
    <form:select path="assignee">
    	<c:forEach var="item" items="${people}">
        	<c:if test="${item.id.equals(task.assignee.id) == true}">
        	<form:option value="${item.id}" label="${item.name}" />
        	</c:if>
            
        </c:forEach>
        <c:forEach var="item" items="${people}">
        	<c:if test="${item.id.equals(task.assignee.id) == false}">
        	<form:option value="${item.id}" label="${item.name}" />
        	</c:if>
            
        </c:forEach>
    </form:select>
            <form:label path="priority">Priority</form:label>
    
    <form:select path="priority">
            <form:option value="0" label="Low" />
            <form:option value="1" label="Medium" />
            <form:option value="2" label="High" />
    </form:select>
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>