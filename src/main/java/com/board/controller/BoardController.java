package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	//게시물 목록
	@GetMapping("/list")
	public void getList(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();
		
		model.addAttribute("list",list);
	}
	//게시물 작성
	@GetMapping("/write")
	public void getWrite() throws Exception{
		
	}
	
	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardVO vo) throws Exception {
	  service.write(vo);
	  
	  return "redirect:/board/list";
	}
	
	//게시물 조회
	@GetMapping("/view")
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception{
		BoardVO vo = service.view(bno);
		model.addAttribute("view",vo);
	}
	
	// 게시물 수정
	@GetMapping("/modify")
	public void getModify(@RequestParam("bno")int bno,Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view",vo);
	}
	@PostMapping("/modify")
	public String postModify(BoardVO vo) throws Exception {
	 service.modify(vo);
	 return "redirect:/board/view?bno=" + vo.getBno();
	}
	
}

