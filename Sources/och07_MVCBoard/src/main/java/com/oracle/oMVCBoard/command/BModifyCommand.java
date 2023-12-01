package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

//	1. Service -> BModifyCommand
//	   1) model Map선언
//	   2) request: parameter ->  bId, bName , bTitle , bContent
//	   3) BDao dao = new BDao();
//	      dao.modify(bId, bName, bTitle, bContent);
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		try {
			// request getParameter 받기 전에 인코딩 적용
			request.setCharacterEncoding("utf-8");
			
			String bId = request.getParameter("bId");
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			
			BDao dao = new BDao();
			dao.modify(bId, bName, bTitle, bContent);  // 이렇게 보내고 되고 parameter가 2개 이상일때 dto에 담아서 보내는 것이 권장
			
		}catch (Exception e) {
			System.out.println("BModifyCommand Exception-> " + e.getMessage());
		}
	}

}
