package org.exchange.repositories.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.exchange.entity.Disk;
import org.exchange.entity.TakenItem;
import org.exchange.repositories.DiskRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DiskRepositoryImpl implements DiskRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public Disk readDiskById(Long id) {
		return manager.find(org.exchange.entity.Disk.class, id);
	}

	@Override
	@Transactional
	public List<Disk> findOwnDisks(Long user_id) {
		return manager.createQuery("SELECT disk FROM Disk disk WHERE disk.owner.account_id = :user_id", org.exchange.entity.Disk.class).setParameter("user_id", user_id).getResultList();
	}

	@Override
	@Transactional
	public List<Disk> findFreeDisks(Long user_id) {
		return manager.createQuery("SELECT disk FROM Disk disk WHERE disk.free = true AND disk.owner.account_id <> :user_id", org.exchange.entity.Disk.class).setParameter("user_id", user_id).getResultList();
	}

	@Override
	@Transactional
	public List<Disk> findTakenDisks(Long user_id) {
		List<Disk> disks = manager.createQuery("SELECT disk FROM Disk disk, TakenItem takenitem WHERE takenitem.account.account_id = :user_id AND disk.free = false AND disk.owner.account_id <> :user_id AND takenitem.disk = disk", org.exchange.entity.Disk.class).setParameter("user_id", user_id).getResultList();
		return disks;
	}


	@Override
	@Transactional
	public List<Disk> findTakenDisksFromUser(Long user_id) {
		List<Disk> disks = manager.createQuery("SELECT disk FROM Disk disk, TakenItem takenitem WHERE disk.free = false AND takenitem.disk = disk AND disk.owner.account_id = :user_id", org.exchange.entity.Disk.class).setParameter("user_id", user_id).getResultList();
		
		for (Disk d: disks)
		{
		   d.getAccounts().size();
		   for (TakenItem takenitem: d.getAccounts())
		   {
			   takenitem.getAccount().getAccount_id();
		   }
		}
		return disks;
	}

	@Override
	@Transactional
	public Disk takeDisk(Long user_id, Long disk_id) {
		
		org.exchange.entity.Account user = manager.find(org.exchange.entity.Account.class, user_id);
		
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		disk.setFree(false);
		manager.merge(disk);
		
		manager.createQuery("DELETE FROM TakenItem t WHERE t.disk = :disk").setParameter("disk", disk).executeUpdate();
		
		org.exchange.entity.TakenItem takenitem = new org.exchange.entity.TakenItem();
		takenitem.setAccount(user);
		takenitem.setDisk(disk);
		
		manager.merge(takenitem);
		
		return disk;
	}

	@Override
	@Transactional
	public Disk returnDisk(Long user_id, Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		manager.createQuery("DELETE FROM TakenItem t WHERE t.disk = :disk").setParameter("disk", disk).executeUpdate();
		disk.setFree(true);	
		return disk;
	}

	@Override
	@Transactional
	public Disk publishDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		manager.createQuery("DELETE FROM TakenItem t WHERE t.disk = :disk").setParameter("disk", disk).executeUpdate();
		disk.setFree(true);	
		return disk;
	}

	@Override
	@Transactional
	public Disk unpublishDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		manager.createQuery("DELETE FROM TakenItem t WHERE t.disk = :disk").setParameter("disk", disk).executeUpdate();
		disk.setFree(false);	
		return disk;
	}

	@Override
	@Transactional
	public Disk createDisk(Long user_id, String name) {
		org.exchange.entity.Disk disk = new org.exchange.entity.Disk();
		disk.setDisk_name(name);
		disk.setFree(false);
		manager.persist(disk);
		
		org.exchange.entity.Account user = manager.find(org.exchange.entity.Account.class, user_id);
		user.getOwn_disks().size();
		user.getOwn_disks().add(disk);
		disk.setOwner(user);
		manager.merge(user);

		return disk;
	}

	@Override
	@Transactional
	public Disk removeDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		manager.createQuery("DELETE FROM TakenItem t WHERE t.disk = :disk").setParameter("disk", disk).executeUpdate();
		manager.remove(disk);
		return disk;
	}

	@Override
	@Transactional
	public Boolean checkTakenDisk(Long user_id, Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		disk.getAccounts().size();
		if (disk.getAccounts().size() > 0)
		  return disk.getAccounts().get(0).getAccount().getAccount_id().equals(user_id);
		else
		  return false;
	}

	@Override
	@Transactional
	public Boolean checkOwnerDisk(Long user_id, Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		System.out.println(user_id.toString() + " " +  disk.getOwner().getAccount_id().toString());
		return disk.getOwner().getAccount_id().equals(user_id);
	}

	


}
