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
<h1>Welcome, ${me.name}</h1>
<h2>Ideas</h2>
<a href="/sort/low">Low Likes</a>
<a href="/sort/high">High Likes</a>
<table>
    <thead>
        <tr>
            <th>Idea</th>
            <th>Created By:</th>
            <th>Likes</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${ideas}" var="idea">
            <tr>
                <td><a href="/ideas/${idea.id}">${idea.name}</a></td>
                <td>${idea.creator.name}</td>
                <td>${idea.likers.size()}</td>
                <td>
                    <c:if test="${idea.likers.contains(me) == false}">
                        <a href="/like/${idea.id}">Like</a>
                    </c:if>
                    <c:if test="${idea.likers.contains(me) == true}">
                        <a href="/unlike/${idea.id}">Unlike</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="/ideas/new">Create Idea</a>
</body>
</html>