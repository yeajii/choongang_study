<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- home.jsp 기본으로 있는거라 인코딩이 안되어 있어서, 위에 두 줄을 넣어주어야 한글이 출력된다 -->
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>  <img alt="반짝" src="resources/img/hot.gif">
</body>
</html>
