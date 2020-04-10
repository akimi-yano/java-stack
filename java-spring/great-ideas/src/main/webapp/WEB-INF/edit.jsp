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
<h1>Edit ${idea.content}</h1>
<p><form:errors path="idea.*"/></p>
<form:form action="/edit/${idea.id}" method="post" modelAttribute="idea">
    <p>
        <form:label path="content">Content</form:label>
        <form:input type="text" path="content"/>
    </p>

    <input type="submit" value="Update"/>
</form:form>  

 <a href="/delete/${item.id}">Delete</a> 
</body>
</html>

