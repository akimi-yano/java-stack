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
<a href="/dash">Go back</a>
<h1>Edit task</h1>
<p><form:errors path="task.*"/></p>
<form:form action="/edit/${task.id}" method="post" modelAttribute="task">
    <p>
        <form:label path="title">Title</form:label>
        <form:input type="text" path="title"/>
    </p>
    <p>
        <form:label path="description">Description</form:label>
        <form:textarea type="text" path="description"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>