package org.exchange.beans.impl;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.exchange.exceptions.AccessException;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope(value = "session")
public class FreeDisksBean implements Serializable {

	@Autowired
	private DiskService disk_service;
	
	private static final long serialVersionUID = 3883553784420723698L;

	public List<org.exchange.entity.Disk> getDisks() {
		return disk_service.findFreeDisks();
	}
	
	public void take(Long disk_id)
	{
		try {
			disk_service.takeDisk(disk_id);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
