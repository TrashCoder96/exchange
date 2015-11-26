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
public class OwnDisksBean implements Serializable {

	private static final long serialVersionUID = -891472667815592277L;

	@Autowired
	private DiskService disk_service;
	
	private String disk_name;
	
	public List<org.exchange.entity.Disk> getDisks() {
	     return disk_service.findOwnDisks();
	}
	
	public void remove(Long disk_id)
	{
		try {
			disk_service.removeDisk(disk_id);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void publish(Long disk_id)
	{
		try {
			disk_service.publishDisk(disk_id);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unpublish(Long disk_id)
	{
		try {
			disk_service.unpublishDisk(disk_id);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create()
	{
		disk_service.createDisk(disk_name);
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}
	
}
