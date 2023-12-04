<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>  <!-- 다운로드 방식, CDM 방식 -->

<script type="text/javascript">
	function getListDept() {
		console.log("getListDept Run..");
		// alert("getListDept Run...");
		
		// http://jsonviewer.stack.hu
		var str  = "";
		var str2 = "";
		$.ajax(
			{
				url      : "/sendVO3",
				dataType : 'json',   // 리스트 형태라서 객체이므로 json 
				success  : function(dept) {
					var jsonStr = JSON.stringify(dept);   // stringify : json이 String으로 변환   2:22 
					alert("jsonStr-> " + jsonStr);
					$('#dept_list_str').append(jsonStr);
					str += "<select name='dept'>";  
					$(dept).each(
						function() {
							str2 = "<option value='" + this.deptno + "'> " + this.dname + "</option>";
							str += str2;
						}		
					)
					str += "</select><p>"
					alert("combobox str-> " + str);
					$('#dept_list_combobox').append(str);     // div 태그 사이, append(데이터 쌓임) 에 html로 되어 있으면 데이터가 쌓이지 않아서 한 개만 적힘  
				}
			}		
		);
	}
	
	
	function getDeptDelete(pIndex){
		var selEmpno = $("#empno" + pIndex).val();   // # = id
		var selEname = $("#ename" + pIndex).val();
		
		// alert("getDeptDelete selEmpno-> " + selEmpno);
		$.ajax(
			{
				url       : "/empnoDelete",
				data      : {empno : selEmpno, ename : selEname},  // key : value    2:48
				dataType  : 'text',   // 객체로 넘어오면 json으로 적음 , dataType = controller에서 return된 테이터 타입 
				success   : function(data) {
					alert(".ajax getDeptDelete data-> " + data);
					if(data == '1') {
						// 성공하면 아래 라인 수행 
						$('#emp' + pIndex).remove();   // delete tag
						
					}
				}
			}		
		);
	}
	
	
	

</script>
</head>
<body>
	<h2>회원 정보</h2>   <!-- varStatus="status" : 한 row씩 작업할 때, 여기서는 delete에 사용 -->
	<table>																
		<tr><th>번호</th><th>사번</th><th>이름</th><th>업무</th><th>부서</th></tr>
		
		  <c:forEach var="emp" items="${listEmp}" varStatus="status">
			<tr id="emp${status.index}"><td>emp${status.index}</td>
			    <td>
			        <input type="hidden" id="deptno${status.index}" value="${emp.deptno }">
			        <input type="text" id="empno${status.index}" value="${emp.empno }">${emp.empno }</td>
			    <td><input type="text" id="ename${status.index}" value="${emp.ename }">${emp.ename }</td>
				<td>${emp.job }</td><td>${emp.deptno } 
				    <input type="button" id="btn_idCheck2" value="사원 Row Delete" onclick="getDeptDelete(${status.index})">
				</td>
			</tr>    
	     </c:forEach>
	</table>

    RestController LISTVO3: <input type="button" id="btn_Dept3"
                                   value="부서명 LIST"  
                                   onclick="getListDept()"><p>
                                   
	dept_list_str:	<div id="dept_list_str"></div>

	dept_list_combobox:
	<div id="dept_list_combobox"></div>
	
	<h1>The End </h1>
</body>
</html>