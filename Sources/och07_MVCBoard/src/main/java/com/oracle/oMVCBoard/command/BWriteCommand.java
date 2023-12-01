package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BWriteCommand implements BCommand {

//	Service
//	1) model이용 , map 선언
//	2) request 이용 ->  bName  ,bTitle  , bContent  추출
//	3) dao  instance 선언
//	4) write method 이용하여 저장(bName, bTitle, bContent)
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		try {
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			
			// view 에 뿌리는 게 아니라 받은 값을 write하는 것이므로 dto로 넘겨줄 수 있지만 3개의 맴버변수 밖에 없어서 그냥 넘긴 거임 
			BDao dao = new BDao();
			dao.write(bName, bTitle, bContent);
			
		}catch (Exception e) {
			System.out.println("BWriteCommand Exception-> " + e.getMessage());
		}

	}

}
