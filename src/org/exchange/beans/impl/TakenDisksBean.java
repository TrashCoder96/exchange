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
public class TakenDisksBean implements Serializable {

	@Autowired
	private DiskService disk_service;
	
	private static final long serialVersionUID = 5881385049588127614L;

	public List<org.exchange.entity.Disk> getDisks() {
		return disk_service.findTakenDisks();
	}
	
	public void returnDisk(Long disk_id)
	{
		try {
			disk_service.returnDisk(disk_id);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
