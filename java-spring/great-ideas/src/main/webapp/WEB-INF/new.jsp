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
<a href="/logout">Logout</a>
<a href="/dash">Go back</a>
<h1>Create a new idea</h1>
<p><form:errors path="task.*"/></p>
<form:form action="/new" method="post" modelAttribute="idea">
    <p>
        <form:label path="content">Content</form:label>
        <form:input type="text" path="content" placeholder="An idea"/>
    </p>
<%--     <p>
        <form:label path="description">Description</form:label>
        <form:textarea type="text" path="description"/>
    </p> --%>
    <input type="submit" value="Create"/>
</form:form>  
</body>
</html>