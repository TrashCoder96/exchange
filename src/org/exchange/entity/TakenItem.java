package org.exchange.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TakenItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long taken_item_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Account account;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Disk disk;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	
}
