package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;


@Controller
public class AddrbookController {

	@Autowired
	AddrBookDAO dao;
	
	@RequestMapping(value="/insert.do")
	public String insert(AddrBookVO vo) throws Exception {
		//1. 클라이언트(웹브라우저)가 보낸 데이터를 req.getParameter해서 꺼내옴 
		System.out.println(vo);
		//2. 꺼내온 값을 DB에 저장 - 저장하기 위한 DAO구문을 활용하면 됨. 
		dao.insertDB(vo);
		return "redirect:addrbook_list.do";
	}
	
	@RequestMapping("/addrbook_form.do")
	public String form() {
		return "addrbook_form";// 끌어오고자 하는 jsp file name을 문자열로 작성
	}
	
//	@RequestMapping("addrbook_list.do")
//	public String list(HttpServletRequest req) throws Exception {
//		// 제어의 흐름을 넘기기 전에 해야할 일: DAO에서 내가 쓰고자 하는 메서드를 끌어온다. 
//		List<AddrBookVO> list = dao.getDBList();
//		// 그 다음 list를 request에 넣는다.
//		req.setAttribute("data", list);
//		return "addrbook_list";
//	}
	
	@RequestMapping("addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
//		if(session.getAttribute("userId")==null) {
//			return "login";
//		}
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	
	@RequestMapping(value="edit.do", method=RequestMethod.GET, params="abId")
	public ModelAndView edit(
			@RequestParam("abId") int abId
			) throws Exception {
		ModelAndView result = new ModelAndView(); 
		AddrBookVO vo = dao.getDB(abId);
		result.addObject("ab", vo);
		result.setViewName("addrbook_edit_form");
		return result;
	}
	
	@RequestMapping(value="update.do", method=RequestMethod.POST, params="abId")
	public String update(
			@RequestParam("abId") int abId, AddrBookVO ab
			) throws Exception {
		dao.updateDB(ab);
		return "redirect:addrbook_list.do";
	}
	
}
