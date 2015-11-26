package org.exchange.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Disk {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long disk_id;
	
	@Column
	private String disk_name;
	
	@OneToMany(mappedBy = "disk", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<TakenItem> accounts;
	
	@Column
	private Boolean free;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private Account owner;

	public Long getDisk_id() {
		return disk_id;
	}

	public void setDisk_id(Long disk_id) {
		this.disk_id = disk_id;
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	public List<TakenItem> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<TakenItem> accounts) {
		this.accounts = accounts;
	}

}
