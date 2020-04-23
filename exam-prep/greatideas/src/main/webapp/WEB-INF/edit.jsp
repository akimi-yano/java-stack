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
<h1>Editing ${idea.name }</h1>
<p><form:errors path="idea.*"/></p>
<form:form action="/ideas/${idea.id }/edit" method="post" modelAttribute="idea">
    <p>
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
    </p>
    <input type="submit" value="Update"/>
</form:form>  
<c:if test="${idea.creator.id == userid }">
	<a href="/delete/${idea.id }">Delete</a>
	</c:if>
</body>
</html>