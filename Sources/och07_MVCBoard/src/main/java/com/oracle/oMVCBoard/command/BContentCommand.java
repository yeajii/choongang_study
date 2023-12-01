package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {  // model에 request 객체 담겨 있음
		// model를 Map으로 전환, map 방식은 key를 주면 value를 돌려줌
		// "request", request
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");  // list.jsp에서 담아온 bId 갖고옴
		
		// 조회용이므로 정보를 view에다 손쉽게 넘기는 방법이 dto 이라서 사용
		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		System.out.println("board.getbId()-> " + board.getbId());
		
		model.addAttribute("mvc_board", board);
	}

}
