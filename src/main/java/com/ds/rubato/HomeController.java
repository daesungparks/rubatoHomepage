package com.ds.rubato;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ds.rubato.dao.MemberDao;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/")
	public String root() {
		
		return "index";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "index";
	}

	@RequestMapping(value = "/board_list")
	public String board_list() {
		
		return "board_list";
	}
	
	@RequestMapping(value = "/board_view")
	public String board_view() {
		
		return "board_view";
	}
	
	@RequestMapping(value = "/board_write")
	public String board_write() {
		
		return "board_write";
	}
	@RequestMapping(value = "/member_join")
	public String member_join() {
		
		return "member_join";
	}
	
	@RequestMapping(value="/member_joinOk",method = RequestMethod.POST)
	public String member_joinok(HttpServletRequest request , Model model) {
		
		String memberid = request.getParameter("mid");
		String memberpw = request.getParameter("mpw");
		String membername = request.getParameter("mname");
		String memberemail = request.getParameter("memail");
		
		MemberDao memberDao =sqlSession.getMapper(MemberDao.class);
		
		memberDao.memberJoinDao(memberid, memberpw, membername, memberemail);
		
		HttpSession session =request.getSession();
		
		session.setAttribute("sessionid", memberid);
		session.setAttribute("sessionName", membername);
		
		String sid = (String) session.getAttribute("sessionId");
		String sname = (String) session.getAttribute("sessionName");
		
		model.addAttribute("memberid",sid);
		model.addAttribute("membername",sname);
		
		return "index";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();//세션 삭제->로그아웃
		
		return "redirect:index";
	}
	

	@RequestMapping(value = "/memberloginOk", method = RequestMethod.POST)
	public String memberloginOk(HttpServletRequest request, Model model) {
		
		String memberid = request.getParameter("mid");
		String memberpw = request.getParameter("mpw");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		int checkIdValue = memberDao.checkIdDao(memberid);
		//DB에 아이디가 존재하면 1이 반환, 없으면 0이 반환
		int checkPwValue = memberDao.checkPwDao(memberid, memberpw);
		//DB에 아이디와 비밀번호가 일치하는 계정이 존재하면 1이 반환, 없으면 0이 반환
		
		model.addAttribute("checkIdValue", checkIdValue);
		model.addAttribute("checkPwValue", checkPwValue);
		
		if(checkPwValue == 1) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("sessionId", memberid);
			
		}
		
		return "loginOk";
	}
	
}
