package org.exchange.controllers.impl;

import org.exchange.services.AccountService;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DiskController {

	@Autowired
	private AccountService user_service;
	
	@Autowired
	private DiskService disk_service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/main_menu", method = RequestMethod.GET)
	public ModelAndView main_menu() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("main_menu");
		return model;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register_get() 
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register_post(@RequestParam(value="email", required=true) String email,
			                  @RequestParam(value="password", required=true) String password) 
	{
		user_service.createUser(email, password);
		return "redirect:main_menu";
	}
	
	
	
}
