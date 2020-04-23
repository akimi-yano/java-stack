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
<h1>Package: ${pack.name}</h1>
<p><form:errors path="pack.*"/></p>
<form:form action="/packages/${pack.id}/edit" method="post" modelAttribute="pack">
    <p>
        <form:label path="cost">Cost:</form:label>
        <form:input type="number" step="0.01" path="cost"/>
    </p>
    <input type="submit" value="edit">
</form:form>
<c:if test="${pack.subscribers.size() == 0 }">
<a href="/delete/${pack.id}">Delete</a>
</c:if>
</body>
</html>