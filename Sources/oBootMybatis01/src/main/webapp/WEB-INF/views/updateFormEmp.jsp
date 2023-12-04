<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>직원정보</h2>
	<form action="updateEmp" method="post">
		<input type="hidden" name="empno" value="${emp.empno }"> 
		<!-- 값을 보여주기만 할거면 hidden 없이 아래만 써도 값은 보이지만, 위에 있는 <input> 태그가 없으면 파라미터가 다음 단계로 못 넘어간다  -->
		
		<table>
			<tr> <th>사번</th> <td>${emp.empno } </tr> 
			<tr>
				<th>이름</th>
				<td><input type="text" name="ename" required="required" value="${emp.ename }"></td>
			</tr>
			<tr>
				<th>업무</th>
				<td><input type="text" name="job" required="required" value="${emp.job }"></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input type="text" name="sal" required="required" value="${emp.sal }"></td>
			</tr>
			<tr>
				<th>입사일</th>
				<td><input type="date" name="hiredate" id="hiredate" value="${emp.hiredate }"></td>
				<!-- type="date" : 달력모양을 사용할 수 있어서 -->
			</tr>
			<tr>
				<th>보너스</th>
				<td><input type="number" name="comm" required="required" value="${emp.comm }"></td>
			</tr>
			<tr>
				<th>관리자 사번</th>
				<td><input type="number" name="mgr" value="${emp.mgr }"></td>
			</tr>
			<tr>
				<th>부서코드</th>
				<td><input type="number" name="deptno" required="required" value="${emp.deptno }"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인">
				</td>
			</tr>
			
			
			
		</table>
		
	</form>

</body>
</html>