package org.exchange.services;

import java.util.List;

import org.exchange.exceptions.AccessException;
import org.springframework.stereotype.Service;

@Service
public interface DiskService {

    public org.exchange.entity.Disk readDiskById(Long id);
	
	public List<org.exchange.entity.Disk> findOwnDisks();
	
	public List<org.exchange.entity.Disk> findFreeDisks();
	
	public List<org.exchange.entity.Disk> findTakenDisks();
	
	public List<org.exchange.entity.Disk> findTakenDisksFromUser();
	
    public org.exchange.entity.Disk takeDisk(Long disk_id) throws AccessException;
    
    public org.exchange.entity.Disk returnDisk(Long disk_id) throws AccessException;
    
    public org.exchange.entity.Disk publishDisk(Long disk_id) throws AccessException;
    
    public org.exchange.entity.Disk unpublishDisk(Long disk_id) throws AccessException;
    
    public org.exchange.entity.Disk createDisk(String name);
    
    public org.exchange.entity.Disk removeDisk(Long disk_id) throws AccessException;
	
}
