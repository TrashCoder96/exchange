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
public class TakenDisksController {

	@Autowired
	private AccountService user_service;
	
	@Autowired
	private DiskService disk_service;
	
	@RequestMapping(value = "/taken_disks", method = RequestMethod.GET)
	public ModelAndView taken_disks_get() 
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
		model.setViewName("taken_disks");
		model.addObject("taken_disks", disk_service.findTakenDisks(user.getAccount_id(), "", 50, 0));
		return model;
	}
	
	@RequestMapping(value = "/taken_disks", method = RequestMethod.POST)
	public ModelAndView taken_disks_post(@RequestParam(value="str", required=true) String str,
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
		model.setViewName("taken_disks");
		model.addObject("taken_disks", disk_service.findTakenDisks(user.getAccount_id(), str, count, position));
		return model;
	}
	
	@RequestMapping(value = "/returnDisk", method = RequestMethod.POST)
	public String returnDisk(@RequestParam(value="disk_id", required=true) Long disk_id)
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.returnDisk(user.getAccount_id(), disk_id);
		return "redirect:taken_disks";		
	}
}
