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
<h1>Create a Show</h1>
<p><form:errors path="show.*"/></p>
<p>${titleUnique }</p>
<p>${titleAndNetwork }</p>
<form:form action="/shows/new" method="post" modelAttribute="show">
    <p>
        <form:label path="title">Title</form:label>
        <form:input path="title"/>
    </p>
    <p>
        <form:label path="network">Network</form:label>
        <form:input path="network"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>