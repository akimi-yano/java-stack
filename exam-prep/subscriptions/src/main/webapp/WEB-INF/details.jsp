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
<p>Your current Package: ${me.subscription.name}</p>
<p>Package Cost: ${me.subscription.cost}</p>

<h2>Switch Package</h2>
<form:form action="/users/${me.id}" method="post" modelAttribute="me">
    <form:select path="subscription">
        <c:forEach var="item" items="${packages}">
            <c:if test="${item.equals(me.subscription) == false}">
                <form:option value="${item.id}" label="${item.name}" />
            </c:if>
        </c:forEach>
    </form:select>
    <input type="submit" value="Switch">
</form:form>
</body>
</html>