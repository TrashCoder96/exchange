package org.exchange.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DiskService {

    public org.exchange.entity.Disk readDiskById(Long id);
	
	public List<org.exchange.entity.Disk> findOwnDisks(Long user_id, String str, Integer limit, Integer position);
	
	public List<org.exchange.entity.Disk> findFreeDisks(Long user_id, String str, Integer limit, Integer position);
	
	public List<org.exchange.entity.Disk> findTakenDisks(Long user_id, String str, Integer limit, Integer positon);
	
	public List<org.exchange.entity.Disk> findTakenDisksFromUser(Long user_id, String str, Integer limit, Integer position);
	
    public org.exchange.entity.Disk takeDisk(Long user_id, Long disk_id);
    
    public org.exchange.entity.Disk returnDisk(Long user_id, Long disk_id);
    
    public org.exchange.entity.Disk publishDisk(Long user_id, Long disk_id);
    
    public org.exchange.entity.Disk unpublishDisk(Long user_id, Long disk_id);
    
    public org.exchange.entity.Disk createDisk(Long user_id, String name);
    
    public org.exchange.entity.Disk removeDisk(Long user_id, Long disk_id);
	
}
