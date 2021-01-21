package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {
	
	//필드
	@Autowired			//자동적으로 객체를 만들어주고 사용할 수 있게 해주는 역할을 해줌.
	private GuestDao guestDao;
	//생성자
	//메소드 g/s
	
	//메소드 일반
	
	//리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookList(Model model) {
		System.out.println("guestbook List");
		
		//dao를 통해 list를 가져온다.
		List<GuestVo> guestList = guestDao.ListAllGuest();
		
		//model을 통해 데이터 전달
		model.addAttribute("gList", guestList);
		
		return "guestList";
	}
	
	//등록 (첫번째 방법)--> @RequestParam을 사용했을 경우
	@RequestMapping(value="/insert2", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookInsert2(@RequestParam("name") String name, 
								  @RequestParam("password") String password,
								  @RequestParam("content") String content) {
		System.out.println("guestbook insert");
		
		//vo
		GuestVo guestVo = new GuestVo(name, password, content);
		//dao --> guestInsert()
		guestDao.guestInsert(guestVo);
		
		return "redirect:/guestbook/list";
	}
	
	//등록 (두번째 방법) --> @ModelAttribute를 사용했을 경우
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String guestbookInsert(@ModelAttribute GuestVo guestVo) {
		System.out.println("guestbook insert");

		//잘되는지 테스트
		//System.out.println(guestVo.toString());

		//dao --> guestInsert()
		guestDao.guestInsert(guestVo);
		
		return "redirect:/guestbook/list";
	}
	
	//삭제폼
	@RequestMapping(value="/guestDelete", method={RequestMethod.GET, RequestMethod.POST})
	public String guestDeleteForm() {
		System.out.println("guestbook DeleteForm");
		
		return "deleteForm";
	}

	//삭제(첫번째 방법) --> @RequestParam을 사용했을 경우
	@RequestMapping(value="/delete2", method={RequestMethod.GET, RequestMethod.POST})
	public String delete2(@RequestParam("no") int no, @RequestParam("password") String password, Model model) {
		System.out.println("guestbook delete");
		
		GuestVo guestVo = new GuestVo(no, password);
		
		//dao --> guestDelete()
		int count = guestDao.guestDelete(guestVo);
		
		if (count == 1) {	//성공
			//리다이렉트
			return "redirect:/guestbook/list";
		} else {	//실패
			
			//model을 통해서 데이터를 전송해주자
			model.addAttribute("result", count);
			//다시 deleteForm 포워드
			return "deleteForm";
		}
	}

	//삭제(두번째 방법) --> @ModelAttribute를 사용했을 경우
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo, Model model) {
		System.out.println("guestbook delete");
		
		//테스트해보자
		//System.out.println(guestVo.toString());
		
		//dao --> guestDelete()
		int count = guestDao.guestDelete(guestVo);
		
		if (count == 1) {	//성공
			//리다이렉트
			return "redirect:/guestbook/list";
		} else {	//실패
			
			//model을 통해서 데이터를 전송해주자
			model.addAttribute("result", count);
			//다시 deleteForm 포워드
			return "deleteForm";
		}
	}

}
