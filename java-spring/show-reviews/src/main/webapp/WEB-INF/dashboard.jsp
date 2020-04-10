<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body >
<div style="width: 500px; margin: 50px 600px">
	<h1>Welcome, ${user.name}</h1>
	<a class="btn btn-primary" href="/logout">Logout</a>
	<a class="btn btn-primary" href="/new">Add a new show</a>
	<p>TV Shows</p>
	<table style="border: 1px solid  black; border-collapse: collapse;">
    <thead>
        <tr  >
            <th style="border: 1px solid  black" >Show</th>
            <th style="border: 1px solid  black">Network</th>
            <th style="border: 1px solid  black">Average Rating</th>
        </tr>
    </thead>
    <tbody >
        <c:forEach items="${shows}" var="item">
        <tr  style="border: 1px solid  black">
            <td style="border: 1px solid  black"><a href = "/show/${item.id}">${item.title}</a></td>
            <td style="border: 1px solid  black">${item.network}</td>
     		<td style="border: 1px solid  black">${item.averageRating}</td>
        </tr>
    	</c:forEach>
    </tbody>
</table>
</div>
</body>
</html>