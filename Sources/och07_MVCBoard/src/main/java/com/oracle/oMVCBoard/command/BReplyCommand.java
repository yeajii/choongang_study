package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BReplyCommand implements BCommand {

//	  1) model이용 , map 선언
//	  2) request 이용 -> bid, bName, bTitle, bContent, bGroup, bStep, bIndent 추출
//	  3) dao  instance 선언
//	  4) reply method  이용하여 댓글 저장 
//	     dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		try {
			// request getParameter 하기 전에 인코딩 해야 됨
			request.setCharacterEncoding("utf-8");
			
			String bId = request.getParameter("bId");
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			String bGroup = request.getParameter("bGroup");
			String bStep = request.getParameter("bStep");
			String bIndent = request.getParameter("bIndent");
			int bIntGroup = Integer.parseInt(request.getParameter("bGroup"));  // int로 바꾸는 이유??
			
			System.out.println("BReplyCommand bIntGroup-> " + bIntGroup);
			
			BDao dao = new BDao();
			dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);  // 홍해의 기적
			
		}catch (Exception e) {
			System.out.println("BReplyCommand Exception-> " + e.getMessage());
		}
	}

}
