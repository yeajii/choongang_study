package com.oracle.oMVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// service는 Dao를 호출해야 해서 연결
		BDao dao = new BDao();
		ArrayList<BDto> boardDtoList = dao.boardList();
		System.out.println("BListCommand boardDtoList.size()-> " + boardDtoList.size());
		
		model.addAttribute("boardList", boardDtoList);
	}

}
