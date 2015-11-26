package org.exchange.beans.impl;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope(value = "session")
public class TakenDisksFromMe implements Serializable {

	@Autowired
	private DiskService disk_service;
	
	private static final long serialVersionUID = -560851555627609061L;

	public List<org.exchange.entity.Disk> getDisks() {
		return disk_service.findTakenDisksFromUser();
	}
	
	
	
}
