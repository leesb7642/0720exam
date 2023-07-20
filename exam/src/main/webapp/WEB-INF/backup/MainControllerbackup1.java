package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.PhonebookService;
import vo.PhonebookVO;

@Controller
public class MainController {
	@Autowired
	PhonebookService pbservice;

	@RequestMapping("/")
	public ModelAndView index() {
		// insert,list,findone 세부분중에 데이터가 필요한 부분(list)
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", pbservice.findAll());
		
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/insert")
	public ModelAndView insert(String name, String hp, String memo) {
		int result=pbservice.insert(new PhonebookVO(name,hp,memo));
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", pbservice.findAll());
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/findone")
	@ResponseBody
	public PhonebookVO findone(int idx) {
		
		return pbservice.findOne(idx); //AJAX 사용시 객체를 JSON으로 바꿔주는 라이브러리가 필요.
	}

}
