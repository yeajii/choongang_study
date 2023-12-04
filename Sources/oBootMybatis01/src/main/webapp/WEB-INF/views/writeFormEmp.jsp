<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chk(){
		if(! frm.empno.value){
			alert("사번을 입력한 후에 중복 체크하세요");  // 입력안하고 '중복확인' 누르면 나온다 
			frm.empno.focus();
			return false;
		} else location.href = "comfirm?empno=" + frm.empno.value;  // 중복 확인 
	}

</script>

</head>
<body>
	<h2>직원 정보 등록</h2>
		<c:if test="${msg != null }">${msg }</c:if>
		
		<form action="writeEmp" method="post" name="frm">
			<table>
				<tr> 
					<th>사번</th> 
					<td>
						<input type="number" name="empno" required="required" maxlength="4" value="${empno }">
						<input type="button" value="중복 확인" onclick="chk()">   <!-- pk인 사번을 입력할 때, 있는 사번인지 없는 사번인지 알 수 있게  검증 버튼 -->
					</td>
				</tr>
									  
				<tr> <th>이름</th> <td><input type="text" name="ename" required="required"></td></tr>
				<tr> <th>업무</th> <td><input type="text" name="job" required="required"></td></tr>
				<tr> <th>급여</th> <td><input type="number" name="sal" required="required"></td></tr>
				<tr> <th>입사일</th> <td><input type="date" name="hiredate" required="required"></td></tr>
				<tr> <th>보너스</th> <td><input type="number" name="comm" required="required"></td></tr>
				
				<tr> 
					<th>관리자 사번</th> <!-- 사용자한테 관리자 사번을 text로 두면 문제생길 수 있어서, 리스트로 만들어서 클릭으로 선택하기 위함 -->
					<td>
						<select name="mgr">
							<c:forEach var="emp" items="${empMngList }">
								<option value="${emp.empno }">${emp.ename }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				
				
				<tr> 
					<th>부서코드</th>  <!-- 부서코드도 text두면 문제생길 수 있어서, 리스트로 만들어서 클릭으로 선택하기 위함 -->
					<td>
						<select name="deptno">
							<c:forEach var="dept" items="${deptList }">
								<option value="${dept.deptno }">${dept.dname }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				
				
				<tr>
					<td colspan="2"><input type="submit" value="확인"></td>
				</tr>
				
				
				
				
			</table>
		</form>
</body>
</html>