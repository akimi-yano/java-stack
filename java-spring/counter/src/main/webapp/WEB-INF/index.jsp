<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<p>You visited <a href="/your_server/">https://your_server/counter</a> ${counter} times</p>

	<a href="/your_server/">Test another visit? +1 </a> |
	
	<a href="/your_server/double">Double increment? +2 </a> |

	<a href="/your_server/reset">Reset</a>
</body>
</html>