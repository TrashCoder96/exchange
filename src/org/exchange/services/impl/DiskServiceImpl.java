package org.exchange.services.impl;

import java.util.List;
import org.exchange.entity.Disk;
import org.exchange.exceptions.AccessException;
import org.exchange.repositories.AccountRepository;
import org.exchange.repositories.DiskRepository;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DiskServiceImpl implements DiskService {

	@Autowired
	private DiskRepository disk_rep;
	
	@Autowired
	private AccountRepository account_rep;
	
	@Override
	public Disk readDiskById(Long id) {
		return disk_rep.readDiskById(id);
	}

	@Override
	public List<Disk> findOwnDisks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		return disk_rep.findOwnDisks(user.getAccount_id());
	}

	@Override
	public List<Disk> findFreeDisks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		return disk_rep.findFreeDisks(user.getAccount_id());
	}

	@Override
	public List<Disk> findTakenDisks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		return disk_rep.findTakenDisks(user.getAccount_id());
	}

	@Override
	public List<Disk> findTakenDisksFromUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		return disk_rep.findTakenDisksFromUser(user.getAccount_id());
	}

	@Override
	public Disk takeDisk(Long disk_id) throws AccessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		if (!disk_rep.checkTakenDisk(user.getAccount_id(), disk_id))
		{
		    return disk_rep.takeDisk(user.getAccount_id(), disk_id);
		}
		else
		{
			throw new AccessException();
		}
	}

	@Override
	public Disk returnDisk(Long disk_id) throws AccessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
	    
		if (disk_rep.checkTakenDisk(user.getAccount_id(), disk_id))
		{
		    return disk_rep.returnDisk(user.getAccount_id(), disk_id);
		}
		else
		{
			throw new AccessException();
		}
	}

	@Override
	public Disk publishDisk(Long disk_id) throws AccessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		if (disk_rep.checkOwnerDisk(user.getAccount_id(), disk_id))
		{
		    return disk_rep.publishDisk(disk_id);
		}
		else
		{
			throw new AccessException();
		}
	}

	@Override
	public Disk unpublishDisk(Long disk_id) throws AccessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
	    
		if (disk_rep.checkOwnerDisk(user.getAccount_id(), disk_id))
		{
		    return disk_rep.unpublishDisk(disk_id);
		}
		else
		{
			throw new AccessException();
		}
	}

	@Override
	public Disk createDisk(String name) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		return disk_rep.createDisk(user.getAccount_id(), name);
	}

	@Override
	public Disk removeDisk(Long disk_id) throws AccessException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    org.exchange.entity.Account user = account_rep.readUserByEmail(email);
		if (disk_rep.checkOwnerDisk(user.getAccount_id(), disk_id))
		{
		    return disk_rep.removeDisk(disk_id);
		}
		else
		{
			throw new AccessException();
		}
	}

	

}
