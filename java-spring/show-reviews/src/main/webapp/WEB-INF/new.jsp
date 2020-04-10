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

<h1>Create a new show</h1>
<p><form:errors path="_____object_____.*"/></p>
<form:form action="/_____object_____" method="post" modelAttribute="Show">
    <p>
        <form:label path="____field1____">Title</form:label>
        <form:input path="____field1____"/>
    </p>
    <p>
        <form:label path="____field2____">Network</form:label>
        <form:textarea path="____field2____"/>
    </p>
   
    <input type="submit" value="Create"/>
</form:form>  

</body>
</html>