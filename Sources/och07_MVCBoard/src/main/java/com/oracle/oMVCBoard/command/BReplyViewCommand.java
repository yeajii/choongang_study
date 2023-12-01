package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BReplyViewCommand implements BCommand {

//	  1) model이용 , map 선언
//	  2) request 이용 ->  bid 추출
//	  3) dao  instance 선언
//	  4) reply_view method 이용하여 (bid)
//	     BDto dto = dao.reply_view(bId);
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);  // dto로 작업 : 모든 컬럼 사용 가능 , bId 조건으로 사용해서 모든 컬럼 select

		model.addAttribute("reply_view", dto);
	}

}
