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
<h1>Create an Idea</h1>
<p><form:errors path="idea.*"/></p>
<form:form action="/ideas/new" method="post" modelAttribute="idea">
    <p>
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>