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
	
<h1>${show.title}</h1>
<p>Network: ${show.network}</p>

	
	<h2>Users who rated this show: </h2>
 <table style="border: 1px solid  black; border-collapse: collapse;">
 <thead>
 <tr>
 	<th style="border: 1px solid  black">Name</th>
 	<th style="border: 1px solid  black">Rating</th>
 </tr>
 </thead>
 <tbody>
  <c:forEach items="${ratings}" var="item">
 <tr>
 	<td style="border: 1px solid  black">${item.getRated_users().getName()}</td>
 	<td style="border: 1px solid  black">${item.getRate()}</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 
         
 <a style="margin:10px 0" class="btn btn-primary" href="/edit/${show.id}">Edit</a> 
 
 <h2>Leave a rating: </h2>
 <p><form:errors path="newRating.*"/></p>
 <form:form action="/show/${show.id}" method="post" modelAttribute="newRating">
    <p>
        <form:label path="rate">Rating</form:label>
        <form:input type="text" path="rate"/>
    </p>

    <input class="btn btn-primary" type="submit" value="Rate!"/>
</form:form> 
</div>
</body>
</html>