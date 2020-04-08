<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p style="color: red">${error}</p>
<form method="POST" action="/test">
<p>What is the code?</p>
<input type="text" name="code">
<button type="submit">Try Code</button>

</form>

</body>
</html>