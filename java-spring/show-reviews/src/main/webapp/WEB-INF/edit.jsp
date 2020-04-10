<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div style="width: 500px; margin: 50px 600px">
<a class="btn btn-primary" href="/logout">Logout</a>
<a class="btn btn-primary" href="/dash">Go back</a>
<h1>Edit ${show.title}</h1>
<p><form:errors path="show.*"/></p>
<form:form action="/edit/${show.id}" method="post" modelAttribute="show">
    <p>
        <form:label path="title">Title</form:label>
        <form:input type="text" path="title"/>
    </p>
        <p>
        <form:label path="network">Network</form:label>
        <form:input type="text" path="network"/>
    </p>

    <input class="btn btn-primary" type="submit" value="Update"/>
</form:form>  

 <a style="margin: 10px 0" class="btn btn-primary" href="/delete/${show.id}">Delete</a> 
 </div>
</body>
</html>

