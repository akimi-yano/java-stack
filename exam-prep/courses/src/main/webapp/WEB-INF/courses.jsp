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
<h1>Welcome , ${me.name}</h1>
<h2>Courses</h2>
<a href="/lowsign">Low Sign Up</a>
<a href="/highsign">High Sign Up</a>
<table>
    <thead>
        <tr>
            <th>Course</th>
            <th>Instructor</th>
            <th>Signups</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${courses}" var="course">
        <tr>
            <td><a href="/courses/${course.id}" >${course.name}</a></td>
            <td>${course.instructor}</td>
            <td>${course.joinedUsers.size()} / ${course.capacity}</td>
            <td>
                <c:if test="${course.joinedUsers.size() == course.capacity}">
                    <p>Full</p>
                </c:if>
                <c:if test="${course.joinedUsers.contains(me) == false}">
                    <a href="/add/${course.id}">Add</a>
                </c:if>
                <c:if test="${course.joinedUsers.contains(me) == true}">
                    <p>Already Added</p>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="/courses/new">Add a course</a>
</body>
</html>