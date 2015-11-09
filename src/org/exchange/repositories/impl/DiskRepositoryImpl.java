package org.exchange.repositories.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.exchange.entity.Disk;
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
		return manager.find(Disk.class, id);
	}

	@Override
	@Transactional
	public List<Disk> findOwnDisks(Long user_id, String str, Integer limit, Integer position)
	{
		String pattern = str + "%";
		return manager.createQuery("SELECT disk FROM Disk disk WHERE (disk.disk_name LIKE :pattern AND disk.owner.account_id = :user_id)", Disk.class).setParameter("pattern", pattern).setParameter("user_id", user_id).getResultList();
	}

	@Override
	@Transactional
	public List<Disk> findFreeDisks(Long user_id, String str, Integer limit, Integer position) {
		String pattern = str + "%";
		return manager.createQuery("SELECT disk FROM Disk disk WHERE (disk.disk_name LIKE :pattern"
				+ " AND "
				+ "disk.owner.account_id <> :user_id "
				+ "AND "
				+ "disk.free = true)", Disk.class).setParameter("pattern", pattern).setParameter("user_id", user_id).getResultList();
	}

	@Override
	@Transactional
	public List<Disk> findTakenDisks(Long user_id, String str, Integer limit, Integer position) {
		String pattern = str + "%";
		return manager.createNativeQuery("SELECT disk.disk_id, disk.disk_name, disk.owner_id, disk.free FROM disk JOIN takenitem ON (disk.disk_id = takenitem.disks_disk_id AND takenitem.accounts_account_id = :user_id)", Disk.class).setParameter("user_id", user_id).getResultList();
	
	}
	
	@Override
	@Transactional
	public Disk takeDisk(Long user_id, Long disk_id) {
		System.out.println(disk_id.toString());
		manager.createNativeQuery("INSERT INTO takenitem(accounts_account_id, disks_disk_id) VALUES(:user_id, :disk_id)").setParameter("user_id", user_id).setParameter("disk_is", disk_id).executeUpdate();
		Disk disk = manager.find(Disk.class, disk_id);
		disk.setFree(false);
		return disk;
	}
	
	@Override
	@Transactional
	public Disk returnDisk(Long user_id, Long disk_id) {
		manager.createNativeQuery("DELETE FROM takenitem WHERE accounts_account_id = :user_id AND disks_disk_id = :disk_id").setParameter("user_id", user_id).setParameter("disk_id", disk_id).executeUpdate();
	    Disk disk = manager.find(Disk.class, disk_id);
	    disk.setFree(true);
		return disk;
	}
	
	@Override
	@Transactional
	public Disk publishDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		disk.setFree(true);
		manager.createNativeQuery("DELETE FROM takenitem WHERE disks_disk_id = :disk_id").setParameter("disk_id", disk_id).executeUpdate();
		return disk;
	}

	@Override
	@Transactional
	public Disk unpublishDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		disk.setFree(false);
		manager.createNativeQuery("DELETE FROM takenitem WHERE disks_disk_id = :disk_id").setParameter("disk_id", disk_id).executeUpdate();
		return disk;
	}

	@Override
	@Transactional
	public Disk createDisk(Long user_id, String name) {
		org.exchange.entity.Disk disk = new org.exchange.entity.Disk();
		org.exchange.entity.Account account = manager.find(org.exchange.entity.Account.class, user_id);
		disk.setOwner(account);
		disk.setDisk_name(name);
		disk.setFree(false);
		manager.merge(disk);
		return disk;
	}

	@Override
	@Transactional
	public Disk removeDisk(Long disk_id) {
		org.exchange.entity.Disk disk = manager.find(org.exchange.entity.Disk.class, disk_id);
		manager.remove(disk);
		return disk;
	}

	@Override
	@Transactional
	public Boolean checkDisk(Long user_id, Long disk_id) {
		return manager.find(org.exchange.entity.Disk.class, disk_id).getOwner().getAccount_id().equals(user_id);
	}

	@Override
	@Transactional
	public List<Disk> findTakenDisksFromUser(Long user_id, String str, Integer limit, Integer position) {
		String pattern = str + "%";
		List<Disk> disks = manager.createQuery("SELECT disk FROM Disk disk WHERE disk.free = false AND disk.owner.account_id = :user_id", Disk.class).setParameter("user_id", user_id).getResultList();
	    for(Disk d: disks)
	    {
	    	d.getAccounts().size();
	    }
		return disks;
	}


}
