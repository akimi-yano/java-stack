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
<h1>${show.title}</h1>
<h2>TV Shows</h2>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Rating</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${show.getRatings()}" var="r">
        <tr>
            <td>
                ${r.rater.name}
            </td>
            <td>${r.rating}</td>
        </tr>
        </c:forEach>
    </tbody>
    
</table>
<a href="/shows/${show.id}/edit">Edit</a>
<c:if test="${showForm}">
<h1>Leave a Rating:</h1>
<p><form:errors path="ratingObject.*"/></p>
<form:form action="/rate/${show.id}" method="post" modelAttribute="ratingObject">
    <p>
        <form:input type="number" path="rating"/>
    </p>
    
    <input type="submit" value="Rate!"/>
    
</form:form>
</c:if>

</body>
</html>