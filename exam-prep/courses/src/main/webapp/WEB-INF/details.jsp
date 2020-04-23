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
<h1>${course.name}</h1>
<p>Instructor: ${course.instructor}</p>
<p>Sign Ups: ${course.joinedUsers.size()}</p>
<a href="/signup/asc/${course.id }">Sign Up Date ASC</a>
<a href="/signup/desc/${course.id }">Sign Up Date DESC</a>

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Sign Up Date</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${joiners}" var="item">
        <tr>
            <td>${item.user.name}</td>
            <td>${item.createdAt}</td>
            <td>
                <c:if test="${item.user.id == userid}">
                    <a href="/remove/${item.course.id}">Remove</a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="/courses/${course.id}/edit">Edit</a>
<a href="/delete/${course.id}">Delete</a>
</body>
</html>