<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- context : 기본 패키지 정보 보여줌 -->
<!-- 프로젝트할 때 폴더가 깊어져서  controller를 못 찾아갈 때 context 걸어주면 해결 -->

	<%
		String context = request.getContextPath();
	%>
	
	context : <%=context %> <p> 
<%-- 	<form action="<%=context%>/studentView1" method="post"> --%>
<%-- 	<form action="<%=context%>/studentView2" method="post"> --%>
	<form action="<%=context%>/studentView2" method="post">
		이름: <input type="text" name="name"><p>
		나이: <input type="text" name="age"><p>
		학년: <input type="text" name="gradeNum"><p>
		반:  <input type="text" name="classNum"><p>
		<input type="submit" value="전송">
	</form>

</body>
</html>