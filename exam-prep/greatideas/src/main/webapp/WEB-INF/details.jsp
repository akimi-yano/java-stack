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
<h1>${idea.name}</h1>
<p>Created By: ${idea.creator.name}</p>
<h2>Users who liked your idea</h2>
<p>Name</p>
<c:forEach items="${idea.likers}" var="person">
    <p>${person.name}</p>
</c:forEach>
<a href="/ideas/${idea.id}/edit">Edit</a>
</body>
</html>