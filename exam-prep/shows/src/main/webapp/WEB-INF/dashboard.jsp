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
<h2>TV Shows</h2>
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Network</th>
            <th>Average Rating</th>
        </tr>
        
    </thead>
    <tbody>
        <c:forEach items="${shows}" var="show">
        <tr>
            <td>
                <a href="/shows/${show.id}">${show.title}</a>
            </td>
            <td>${show.network}</td>
            <td>${show.average}</td>
        </tr>
        </c:forEach>
    </tbody>
    
</table>
<a href="/shows/new">Add a show</a>
</body>
</html>