package org.exchange.controllers.impl;

import java.util.ArrayList;
import org.exchange.entity.Disk;
import org.exchange.services.AccountService;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TakenDisksFromUserController {

	@Autowired
	private AccountService user_service;
	
	@Autowired
	private DiskService disk_service;
	
	@RequestMapping(value = "/user_taken_disks", method = RequestMethod.GET)
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
		model.setViewName("user_taken_disks");
		model.addObject("user_taken_disks", 
		    		disk_service.findTakenDisksFromUser(
		    				user.getAccount_id(), 
		    				"",
		    				50, 
		    				0));
		return model;
	}
	
	@RequestMapping(value = "/user_taken_disks", method = RequestMethod.POST)
	public ModelAndView taken_disks_from_user_post() 
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
		model.setViewName("user_taken_disks");
		model.addObject("user_taken_disks", 
		    		disk_service.findTakenDisksFromUser(
		    				user.getAccount_id(), 
		    				"",
		    				50, 
		    				0));
		return model;
	}
	
}
