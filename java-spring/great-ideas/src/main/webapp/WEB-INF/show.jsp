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
	<a href="/logout">Logout</a>
	<a href="/dash">Go back</a>
	
<h1>${idea.content}</h1>
<p>Created by: ${idea.creator.name}</p>

	
	<p>Users who liked your idea: </p>
 <table>
 <thead>
 <tr><th>Name</th></tr>
 </thead>
 <tbody>
  <c:forEach items="${idea.liked_users}" var="item">
 <tr>
 <td>${item.name}</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 
         
 <a href="/edit/${idea.id}">Edit</a> 

</body>
</html>