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
	<h1>Hello, ${user.name}</h1>
	<a href="/newTask">Create a new Task</a>
	<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Creator</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${tasks}" var="item">
        <tr>
            <td>${item.title}</td>
            <td>${item.description}</td>
            <td>${item.creator.name}</td>
            <td>
                <a href="/edit/${item.id}">Edit</a> |
                <a href="/delete/${item.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>