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
	<h1>Welcome, ${user.name}</h1>
	<a href="/logout">Logout</a>
	<a href="/new">Create a new idea</a>
	<p>Ideas</p>
	<table>
    <thead>
        <tr>
            <th>Ideas</th>
            <th>Created by</th>
            <th>Likes</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${ideas}" var="item">
        <tr>
            <td><a href = "/show/${item.id}">${item.content}</a></td>
            <td>${item.creator.name}</td>
            <td>${(item.liked_users).size()}</td>
            <c:if test ="${item.liked_users.contains(user)}">
            <td><a href ="/unlike/${item.id}">Unlike</a>
            
            </td>
            </c:if>
                <c:if test ="${!item.liked_users.contains(user)}">
            <td><a href="/like/${item.id}">Like</a>
   
            </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>