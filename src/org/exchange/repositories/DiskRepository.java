package org.exchange.repositories;

import java.util.List;

import org.exchange.entity.Disk;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskRepository {
	
	public org.exchange.entity.Disk readDiskById(Long id);
	
	public List<org.exchange.entity.Disk> findOwnDisks(Long user_id);
	
	public List<org.exchange.entity.Disk> findFreeDisks(Long user_id);
	
	public List<org.exchange.entity.Disk> findTakenDisks(Long user_id);
	
	public List<org.exchange.entity.Disk> findTakenDisksFromUser(Long user_id);
	
    public org.exchange.entity.Disk takeDisk(Long user_id, Long disk_id);
    
    public Disk returnDisk(Long user_id, Long disk_id);
    
    public org.exchange.entity.Disk publishDisk(Long disk_id);
    
    public org.exchange.entity.Disk unpublishDisk(Long disk_id);
    
    public org.exchange.entity.Disk createDisk(Long user_id, String name);
    
    public org.exchange.entity.Disk removeDisk(Long disk_id);
    
    public Boolean checkTakenDisk(Long user_id, Long disk_id);
    
    public Boolean checkOwnerDisk(Long user_id, Long disk_id);
    
    
    
}
