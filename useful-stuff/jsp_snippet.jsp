<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<form:select path="name_of_column">
   <c:forEach var="item" items="${name_of_list}">
   	  <form:option value="${item.id}" label="${item.field_to_display}"/>
   </c:forEach>
</form:select>