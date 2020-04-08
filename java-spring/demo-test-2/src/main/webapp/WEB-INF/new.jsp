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
<a href="/">Go back</a>
	<h1>New Book</h1>
<p><form:errors path="book.*"/></p>
<form:form action="/new" method="post" modelAttribute="book">
    <p>
        <form:label path="title">Title</form:label>
        <form:input type="text" path="title"/>
    </p>
    <p>
        <form:label path="description">Description</form:label>
        <form:textarea path="description"/>
    </p>
    <p>
        <form:label path="pageCount">PageCount</form:label>
        <form:input type="number" path="pageCount"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>