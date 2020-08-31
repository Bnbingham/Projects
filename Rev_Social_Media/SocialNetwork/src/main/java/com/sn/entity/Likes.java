package com.sn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIKES_TABLE")
public class Likes {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKES_PK_SEQ")
    private Integer id;
    
    @Column(name = "POST_ID")
    private Integer pId;
	
	@Column(name = "COMMENT_ID")
	private Integer cId;
	
	@Column(name = "USERNAME")
	private String username;

	public Likes(Integer id, Integer pId, Integer cId, String username) {
		super();
		this.id = id;
		this.pId = pId;
		this.cId = cId;
		this.username = username;
	}

	public Likes() {
		super();
	}

	public Likes(Integer pId, Integer cId) {
		super();
		this.pId = pId;
		this.cId = cId;
	}

	public Likes(String username) {
		super();
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Likes [id=" + id + ", pId=" + pId + ", cId=" + cId + ", username=" + username + "]";
	}
	
}
