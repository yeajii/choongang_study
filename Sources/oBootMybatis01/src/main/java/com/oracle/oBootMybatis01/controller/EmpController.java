package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j   
public class EmpController {
	
	private final EmpService es;  			   // service와 연결
	private final JavaMailSender mailSender;   // mail 전송 객체 
	
	
	@RequestMapping(value = "listEmp")
	public String empList(Emp emp, String currentPage, Model model) {  // 파라미터가 여러 개라 dto로 받음
		System.out.println("EmpController Start listEmp...");
		
		// Emp 전체 Count  
		int totalEmp = es.totalEmp();
		
		// paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// parameter Emp --> page만 추가 세팅
		emp.setStart(page.getStart());  // 시작시 1
		emp.setEnd(page.getEnd());		// 시작시 10
				
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController empList listEmp.size()-> " + listEmp.size());

		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", page);
		
		return "list";
	}
	
// -----------------------------------------------------------------------
	
//	1. EmpService안에 detailEmp method 선언
//	   1) parameter : empno
//	   2) Return      Emp
//
//	2. EmpDao   detailEmp method 선언 
	
//  	                    mapper ID   ,    Parameter
//	emp = session.selectOne("tkEmpSelOne",    empno);
	
	// 상세정보
	@GetMapping(value = "detailEmp")
	public String detailEmp(int empno, Model model) {   // 파라미터가 하나라서 dto로 안 받음
		System.out.println("EmpController detailEmp Start...");
		
		Emp detailEmp = es.detailEmp(empno);
		System.out.println("EmpController detailEmp detailEmp-> " + detailEmp);
		
		model.addAttribute("emp", detailEmp);
		
		// 선생님 거
		// Emp emp = es.detailEmp(empno);
		// model.addAttribute("emp", emp);
		
		return "detailEmp";
	}
	
// --------------------------------------------------------------------------
	
	// 수정 (상세정보 가져오기 : 정보가 화면에 보이기 위해)
	@GetMapping(value = "updateFormEmp")  
	public String updateFormEmp(int empno, Model model) {
		System.out.println("EmpController updateFormEmp Start...");
		
		Emp emp = es.detailEmp(empno);
		System.out.println("EmpController updateFormEmp emp.getEname()-> " + emp.getEname());
		System.out.println("EmpController updateFormEmp emp.getHiredate()-> " + emp.getHiredate());  
		// hiredate가 DB에서 data형이고 Emp dto에서 String인데, 값을 갖고 왔는지 확인하기 위함 
		
		// 문제 
		// 1. DTO  String hiredate
		// 2. View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3. 해결책  : 년월일만 짤라 넣어 주어야 함
		
		// *** <input>태그에 type="date" 할 때 항상 아래와 같이 코딩하기 ***
		String hiredate = "";     // "" = null = 공백 
		if (emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0, 10);  //  달력의 - 때문에 10으로 함 
			emp.setHiredate(hiredate);
		}
		System.out.println("EmpController updateFormEmp hiredate-> " + hiredate);
		
		model.addAttribute("emp", emp);
		
		return "updateFormEmp";
	}
	
	// 수정 (update)
	@PostMapping(value = "updateEmp")
	public String updateEmp(Emp emp, Model model) {
		log.info("EmpController updateEmp Start...");
		
//      1. EmpService안에 updateEmp method 선언
//      1) parameter : Emp
//      2) Return      updateCount (int)
//
//      2. EmpDao updateEmp method 선언
//                                    mapper ID   ,    Parameter
//      updateCount = session.update("tkEmpUpdate",emp);
		
		int updateCount = es.updateEmp(emp);
		System.out.println("EmpController updateEmp updateCount-> " + updateCount);
		
		model.addAttribute("upCnt", updateCount);   // Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test");  // Test Controller간 Data 전달
		
	//	return "forward:listEmp";   // upCnt가 뜬다,   forward  : model에 담겨 있는 게 listEmp의 Model로 저장됨
		return "redirect:listEmp";  // upCnt가  안 뜬다, redirect : model에 담겨 있는 게 listEmp의 Model로 저장 안됨 
	}								// 차이점 : 페이지 이동할 때 redirect는 값을 가지고 이동하지 않고, forward는 값을 가지고 이동한다
									// 공통점 : 같은 controller 내의 페이지 이동할 메소드를 찾는다 
// --------------------------------------------------------------------------	
	
	// 새 글 작성
	@RequestMapping(value = "writeFormEmp")
	public String writeFormEmp(Model model) {
		System.out.println("EmpController writeFormEmp Start...");
		
		// 관리자 사번만 get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController empList.size()-> " + empList.size());
		model.addAttribute("empMngList", empList);
		
		// 부서(코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);  // dept
		System.out.println("EmpController deptList deptList.size()-> " + deptList.size());
		
		return "writeFormEmp";
	}
	
	// empno 중복 확인 
		@GetMapping(value = "comfirm")
		public String comfirm(int empno, Model model) {
			Emp emp = es.detailEmp(empno);   // 사번을 주면 Dto 돌려주는 메소드 
			
			if(emp != null) {  // null 아닐 때 
				System.out.println("EmpController comfirm 중복된 사번입니다");
				model.addAttribute("msg", "중복된 사번 입니다");
				return "forward:writeFormEmp";
				
			} else {          // null이 나왔을 때 
				System.out.println("EmpController comfirm 사용 가능한 사번 입니다");
				model.addAttribute("msg", "사용 가능한 사번 입니다");
				return "forward:writeFormEmp";
			}
		}
	
	// 새 글 작성 -> insert
	@PostMapping(value = "writeEmp")
	public String writeEmp(Emp emp, Model model) {
		System.out.println("EmpController writeEmp Start...");
		
		// Service, Dao, Mapper명 [insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp);
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력 실패입니다");
			return "forward:writeFormEmp";
		}
	}
// ------------------------------------------------------------------	
	
	// Validation시 참조
	@PostMapping(value = "writeEmp3")
	public String writeEmp3(@ModelAttribute("emp") @Valid Emp emp
							, BindingResult result
							, Model model) {
		System.out.println("EmpController writeEmp3 Start...");
		
		// validation 오류시 result
		if(result.hasErrors()) {
			System.out.println("EmpController writeEmp3 hasErrors...");
			model.addAttribute("msg", "BindingResult 입력 실패입니다. 확인해보세요");
			return "forward:writeFormEmp3";
		}
		
		// Service, Dao, Mapper명 [insertEmp] 까지 -> insert
		int insertResult = es.insertEmp(emp);
		if (insertResult > 0) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력 실패입니다");
			return "forward:writeFormEmp3";   // 입력실패해도 다시 작성하라고 writeFormEmp3로 이동하게 함 
		}
		
	}	
		
	@RequestMapping(value = "writeFormEmp3")
	public String writeFormEmp3(Model model) {
		System.out.println("EmpController writeFormEmp3 Start...");
			
		// 관리자 사번만 get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController empList.size()-> " + empList.size());
		model.addAttribute("empMngList", empList);
			
		// 부서(코드, 부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);  // dept
		System.out.println("EmpController deptList deptList.size()-> " + deptList.size());
		
		return "writeFormEmp3";
	}
	
// --------------------------------------------------------------------------		
	
	// Controller -->  deleteEmp    1.parameter : empno
	// name -> Service, dao , mapper
	// return -> listEmp
	
	// 삭제 
	@RequestMapping(value = "deleteEmp")
	public String deleteEmp(int empno, Model model) {
		System.out.println("EmpController deleteEmp Start...");
		int result = es.deleteEmp(empno);
		
		return "redirect:listEmp"; 
	}
	
// --------------------------------------------------------------------------		
	
	// 검색 기능 
	@RequestMapping(value = "listSearch3")
	public String listSearch3(Emp emp, String currentPage, Model model) {  // 파라미터가 여러 개라 dto로 받음
		System.out.println("EmpController Start listSearch3...");
		
		// 검색 Count 갯수 
		int totalEmp = es.condTotalEmp(emp);
		System.out.println("EmpController totalEmp-> " + totalEmp);
		
		// paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// parameter Emp --> page만 추가 세팅
		emp.setStart(page.getStart());  // 시작시 1
		emp.setEnd(page.getEnd());		// 시작시 10
				
		List<Emp> listSearchEmp = es.listSearchEmp(emp);
		System.out.println("EmpController listSearch3 listEmp.size()-> " + listSearchEmp.size());

		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listSearchEmp);
		model.addAttribute("page", page);
		
		return "list";
	}
	// 위에 empList메소드에 있는 totalEmp과 동일해서 다른 이름으로 변경해도 되지만, 
	// list.jsp에 있는 totalEmp를 재활용하기 위해 변경 안함 
	
// --------------------------------------------------------------------------
	
	// join해서 직원부서조회  (dept에 있는 loc를 갖고 오기 위해 조인)
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController listEmpDept Start...");
		
		// Service ,DAO -> listEmpDept
		// Mapper만 -> tkListEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		System.out.println("EmpController listEmpDept listEmpDept.size()-> " + listEmpDept.size());
		
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
	}
	
// --------------------------------------------------------------------------
	
	// mail 
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailSending...");
		String tomail = "yeji7732@naver.com";       // 받는 사람 이메일 
		System.out.println(tomail);
		String setfrom = "chayeji0317@gmail.com";   // 보내는 사람 이메일 
		String title = "mailTransport 입니다";        // 제목
		
		try {
			// SMTP(Simple Mail Transfer Protocol) 안에 Mime
			// Mime : 전자 우편 Internet 표준 format  
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);			 // 보내는 사람 생략하면 정상 작동을 안함
			messageHelper.setTo(tomail);			 // 받는 사람 이메일 
			messageHelper.setSubject(title);		 // 메일 제목은 생략이 가능하지만, 스팸으로 간주될 수 있어서 작성을 권장  
			String tempPassword = (int)(Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다: " + tempPassword); // 매일 내용 
			System.out.println("임시 비밀번호입니다: " + tempPassword);
			
			DataSource dataSource = new FileDataSource("c:\\log\\hwa.png");
			messageHelper.addAttachment(MimeUtility.encodeText("hwa3.png", "UTF-8", "B"), dataSource); // addAttachment : 첨부문서 형성되어짐
			mailSender.send(message);        // yml에 있는 정보로 전송  
			model.addAttribute("check", 1);  // 메일 정상 전달 
			// DB Logic 구성 자리 -> 메일 전송에 성공하면  발급된 임시 비밀번호로 현재 비밀번호를 업데이트하는 로직 구성해야함
			
		} catch (Exception e) {
			System.out.println("mailTransport  e.getMessage()-> " + e.getMessage());
			model.addAttribute("check", 2);   // 메일 전달 실패 
		}		
		return "mailResult";
	}
		
// --------------------------------------------------------------------------	
	
	// PL/SQL(부서입력)  10/19
	// Procedure Test 입력하면
	@RequestMapping(value = "writeDeptIn")
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn Start...");
		
		return "writeDept3";
	}
	
	// Procedure 통한 Dept 입력 후 VO 전달 
	@PostMapping(value = "writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		es.insertDept(deptVO);
		
		if(deptVO == null) {
			System.out.println("deptVO null");
		} else {
			System.out.println("deptVO.getOdeptno()-> " + deptVO.getOdeptno());
			System.out.println("deptVO.getOdname()-> " + deptVO.getOdname());
			System.out.println("deptVO.getOloc()-> " +  deptVO.getOloc());
			model.addAttribute("msg", "정상 입력");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
// --------------------------------------------------------------------------		

	// PL/SQL(부서조회 Cursor) 프로시저로 커서문을 사용해서 멀티 로우 갖고 옴 12:18    10/20
	// Map 적용 
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor Start...");
		
		// 부서범위 조회 -> 전체가 아닌 부분만 보고 싶을 떄 
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);  // 시작
		map.put("eDeptno", 55);  // 끝
		es.selListDept(map);
		
		// 잘 갖고 오는지 확인하기 위함 
		List<Dept> deptLists = (List<Dept>) map.get("dept");
		for (Dept dept : deptLists) {
			System.out.println("dept.getDname()-> " + dept.getDname());
			System.out.println("dept.getLoc()-> " + dept.getLoc());
		}
		System.out.println("deptLists.size()-> " + deptLists.size());
		model.addAttribute("deptList", deptLists);
		
		return "writeDeptCursor";
	}
	
// --------------------------------------------------------------------------		
	
	// interCetpor 시작화명 
	@RequestMapping(value = "interCeptorForm")
	public String interCeptorForm(Model model) {
		System.out.println("interCetporForm Start...");
		
		return "interCeptorForm";
	}
	
	// 2번   interCeptor Number 2
	@RequestMapping(value = "interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("EmpController interCeptor Test Start...");
		System.out.println("EmpController interCeptor id-> " + id);
		
		// 존재 1, 비존재 0
		int memCnt = es.memCount(id);
		System.out.println("EmpController memCnt-> " + memCnt);
		
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		System.out.println("interCeptor Test End");
		
		return "interCeptor";  // user 존재하면 user 이름 조회 page
	}
	
	
	//  sampleinterCeptor 내용을 받아 처리 (memCnt < 1)
	@RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	public String doMemberWrite(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite 부터 하세요");
		model.addAttribute("id", ID);
		
		return "doMemberWrite";
	}
	
	// interCeptor 진행 test (ID가 존재할 때)
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("EmpController doMemberList ID-> " + ID);
		
		Member1 member1 = null;
		// Member1 List get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		
		return "doMemberList";   // user 존재하면 user 이름 조회 page
	}
		
// --------------------------------------------------------------------------		
	
	// ajaxForm 입력화면
	@RequestMapping(value = "ajaxForm")
	public String ajaxForm(Model model) {
		System.out.println("ajaxForm Strat...");
		
		return "ajaxForm";
	}
	
	
	// 10:36
	@ResponseBody 
	@RequestMapping(value = "getDeptName")
	public String getDeptName(int deptno, Model model) {
		System.out.println("getDeptName deptno-> " + deptno);
		String deptName = es.deptName(deptno);   // 부서 코드를 주면 부서명을 갖고 옴 
		System.out.println("getDeptName deptName-> " + deptName);
		
		return deptName;
	}
	
	
	// 아작스 화면으로 이동, 아직 아작스 아님 
	@RequestMapping(value = "listEmpAjaxForm")
	public String listEmpAjaxForm(Model model) {
		System.out.println("Ajax List Test Start..");
		
		Emp emp = new Emp();
		// 파라미터 emp -> page만 추가 setting 
		emp.setStart(1);  // 시작시 1
		emp.setEnd(10);   // 시작시 15
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("listEmp.size()-> " + listEmp.size());
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		
		return "listEmpAjaxForm";
	}
	
	// 
	@RequestMapping(value = "listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		System.out.println("listEmpAjaxForm2 Start...");
		
		Emp emp = new Emp();
		System.out.println("Ajax List Test Start..");
		
		// 파라미터 emp -> page만 추가 setting 
		emp.setStart(1);  // 시작시 1
		emp.setEnd(15);   // 시작시 15
		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("listEmp", listEmp);
		
		return "listEmpAjaxForm2";
	}
	
	
	
	
	

	
	
	
	
	
	
}
