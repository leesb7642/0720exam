package controller;

import java.io.Console;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void Scheduled() {
		System.out.println("컨트롤러 작동중"+":"+new Date());
		
	}
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", pbservice.findAll());
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/insert")
	public ModelAndView insert(String name, String hp, String memo) {
		int result = pbservice.insert(new PhonebookVO(name, hp, memo));
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", pbservice.findAll());
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/findone")

	public ModelAndView findone(int idx) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("phonebook", pbservice.findOne(idx));
		mav.addObject("list", pbservice.findAll());
		mav.setViewName("index");
		return mav; // AJAX 사용시 객체를 JSON으로 바꿔주는 라이브러리가 필요.
	}

	@RequestMapping("/search")
	public ModelAndView search(String keyword) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("keyword", pbservice.search(keyword));
		mav.addObject("list", pbservice.findAll());
		mav.setViewName("index");

		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView update(int idx, PhonebookVO phonebook) {

		ModelAndView mav = new ModelAndView();
		int result = pbservice.update(idx, phonebook);
		mav.addObject("phonebook", pbservice.findOne(idx));
		mav.addObject("result", result);
		mav.setViewName("result");

		return mav;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(int idx) {
		ModelAndView mav = new ModelAndView();
		int result = pbservice.delete(idx);
		mav.addObject("result", result);
		;
		mav.setViewName("result");
		return mav;
	}

}
