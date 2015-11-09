package org.exchange.controllers.impl;

import org.exchange.services.AccountService;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeDisksController {

	@Autowired
	private AccountService user_service;
	
	@Autowired
	private DiskService disk_service;
	
	@RequestMapping(value = "/free_disks", method = RequestMethod.GET)
	public ModelAndView free_disks_get() 
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		ModelAndView model = new ModelAndView();
		model.setViewName("free_disks");
		model.addObject("free_disks", disk_service.findFreeDisks(user.getAccount_id(), "", 50, 0));
		return model;
	}
	
	@RequestMapping(value = "/free_disks", method = RequestMethod.POST)
	public ModelAndView free_disks_post(@RequestParam(value="str", required=true) String str,
			                           @RequestParam(value="count", required=true) Integer count,
			                           @RequestParam(value="position", required=true) Integer position) 
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		ModelAndView model = new ModelAndView();
		model.setViewName("free_disks");
		model.addObject("free_disks", disk_service.findFreeDisks(user.getAccount_id(), str, count, position));
		return model;
	}
	
	@RequestMapping(value = "/takeDisk", method = RequestMethod.POST)
	public String takeDisk(@RequestParam(value="disk_id", required=true) Long disk_id)
	{
		UserDetails userDetails = null;
		System.out.println(disk_id);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.takeDisk(user.getAccount_id(), disk_id);
		return "redirect:free_disks";
		
	}
	
	
}
