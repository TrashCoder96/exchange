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
public class OwnDisksController {

	@Autowired
	private AccountService user_service;
	
	@Autowired
	private DiskService disk_service;
	
	@RequestMapping(value = "/own_disks", method = RequestMethod.GET)
	public ModelAndView own_disks_get() 
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
		model.setViewName("own_disks");
		model.addObject("own_disks", disk_service.findOwnDisks(user.getAccount_id(), "", 50, 0));
		return model;
	}
	
	@RequestMapping(value = "/own_disks", method = RequestMethod.POST)
	public ModelAndView own_disks_post(@RequestParam(value="str", required=true) String str,
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
		model.setViewName("own_disks");
		model.addObject("own_disks", disk_service.findOwnDisks(user.getAccount_id(), str, count, position));
		return model;
	}

	@RequestMapping(value = "/createDisk", method = RequestMethod.POST)
	public String createDisk(@RequestParam(value="disk_name", required=true) String disk_name)
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.createDisk(user.getAccount_id(), disk_name);
	    return "redirect:own_disks";
	}
	
	@RequestMapping(value = "/removeDisk", method = RequestMethod.POST)
	public String removeDisk(@RequestParam(value="disk_id", required=true) Long disk_id)
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.removeDisk(user.getAccount_id(), disk_id);
		return "redirect:own_disks";
	}
	
	@RequestMapping(value = "/publishDisk", method = RequestMethod.POST)
	public String publishDisk(@RequestParam(value="disk_id", required=true) Long disk_id)
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.publishDisk(user.getAccount_id(), disk_id);
		return "redirect:own_disks";
	}
	
	@RequestMapping(value = "/unpublishDisk", method = RequestMethod.POST)
	public String unpublishDisk(@RequestParam(value="disk_id", required=true) Long disk_id)
	{
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			userDetails = (UserDetails)principal;
		}
		String email = userDetails.getUsername();
		org.exchange.entity.Account user = user_service.readUser(email);
		disk_service.unpublishDisk(user.getAccount_id(), disk_id);
		return "redirect:own_disks";
	}
	
}
