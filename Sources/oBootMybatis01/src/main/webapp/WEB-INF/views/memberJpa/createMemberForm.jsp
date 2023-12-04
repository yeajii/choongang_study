<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Mybatis JPA 회원등록</h1>
	<div class="container">
					<!-- 폴더가 깊어지면  환경을 잊어버릴 수 있으므로 -->
		<form action="${pageContext.request.contextPath }/memberJpa/save" method="post">
			Id : <input type="text" id="id" name="id" required="required"><p>
			이름 : <input type="text" id="name" name="name"     placeholder="이름을 입력하세요"><p>
			PW : <input type="text" id="pw"    name="password" placeholder="PW를 입력하세요">
			<button type="submit">등록</button>
		</form>
		
		<a href="/members">JPA Member List 조회</a><p>
	</div>

</body>
</html>