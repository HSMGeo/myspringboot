package lx.edu.springboot.controller;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;

@Controller
public class HelloController {
	
	@Autowired
	AddrBookDAO dao;
	
	@RequestMapping("/hello")
	public String hello(Model model) throws Exception {
		AddrBookVO vo = dao.getDB(1);
		model.addAttribute("vo", vo);
		return "hello";
	}
	
	public void nono() {
		System.out.print("언니..... 나랑 짝꿍하느라 고생했고.... 감기 옮으면 언니가 대신 우리팀 프로젝트 해죠라......");
	}
}
