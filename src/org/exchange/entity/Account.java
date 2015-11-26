package org.exchange.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;

@Entity
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE, region = "org.exchange.entity.Account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long account_id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy = "account", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<TakenItem> disks;
	
	@OneToMany(mappedBy = "owner", fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Disk> own_disks;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public List<Disk> getOwn_disks() {
		return own_disks;
	}

	public void setOwn_disks(List<Disk> own_disks) {
		this.own_disks = own_disks;
	}

	public List<TakenItem> getDisks() {
		return disks;
	}

	public void setDisks(List<TakenItem> disks) {
		this.disks = disks;
	}
	
}
