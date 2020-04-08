<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/new">Create a Book!</a>
	<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Pages</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${books}" var="item" >
            <tr>
                <td>${item.title}</td>
                <td>${item.description}</td>
                <td>${item.pageCount}</td>
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