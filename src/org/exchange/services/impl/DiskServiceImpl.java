package org.exchange.services.impl;

import java.util.List;

import org.exchange.entity.Disk;
import org.exchange.repositories.DiskRepository;
import org.exchange.services.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiskServiceImpl implements DiskService {

	@Autowired
	private DiskRepository disk_rep;

	@Override
	public Disk readDiskById(Long id) {
		return disk_rep.readDiskById(id);
	}

	@Override
	public List<Disk> findOwnDisks(Long user_id, String str, Integer limit, Integer position) {
		return disk_rep.findOwnDisks(user_id, str, limit, position);
	}

	@Override
	public List<Disk> findFreeDisks(Long user_id, String str, Integer limit, Integer position) {
		return disk_rep.findFreeDisks(user_id, str, limit, position);
	}

	@Override
	public List<Disk> findTakenDisks(Long user_id, String str, Integer limit, Integer position) {
		return disk_rep.findTakenDisks(user_id, str, limit, position);
	}

	@Override
	public List<Disk> findTakenDisksFromUser(Long user_id, String str, Integer limit, Integer position) {
		return disk_rep.findTakenDisksFromUser(user_id, str, limit, position);
	}

	@Override
	public Disk takeDisk(Long user_id, Long disk_id) {
		return disk_rep.takeDisk(user_id, disk_id);
	}

	@Override
	public Disk publishDisk(Long user_id, Long disk_id) {
		if (disk_rep.checkDisk(user_id, disk_id))
		{
		    return disk_rep.publishDisk(disk_id);
		}
		else
		{
		    return null;
		}
	}

	@Override
	public Disk unpublishDisk(Long user_id, Long disk_id) {
		if (disk_rep.checkDisk(user_id, disk_id))
		{
		    return disk_rep.unpublishDisk(disk_id);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Disk createDisk(Long user_id, String name) {
		return disk_rep.createDisk(user_id, name);
	}

	@Override
	public Disk removeDisk(Long user_id, Long disk_id) {
		return disk_rep.removeDisk(disk_id);
	}

	@Override
	public Disk returnDisk(Long user_id, Long disk_id) {
		return disk_rep.returnDisk(user_id, disk_id);
	}

}
