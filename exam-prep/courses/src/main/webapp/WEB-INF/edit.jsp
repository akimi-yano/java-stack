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
<h1>Edit ${course.name }</h1>
<p><form:errors path="course.*"/></p>
<form:form action="/courses/${course.id }/edit" method="post" modelAttribute="course">
    <p>
        <form:label path="name">Name</form:label>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="instructor">Instructor</form:label>
        <form:input path="instructor"/>
    </p>
    <p>
        <form:label path="capacity">Capacity</form:label>
        <form:input type="number" path="capacity"/>
    </p>
    <input type="submit" value="Submit"/>
</form:form> 
</body>
</html>