package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BDeleteCommand implements BCommand {

//	 1) model이용 , map 선언
//	 2) request 이용 ->  bId 추출
//	 3) dao  instance 선언
//	 4) dao.delete method 이용하여 삭제
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}
}
