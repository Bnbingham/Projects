package com.sn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT_TABLE")
public class Comment {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_PK_SEQ")
    private Integer id;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "POST_ID")
    private Integer pId;
    
    @Column(name = "BODY")
    private String body;

	public Comment() {
		super();
	}

	
	
	public Comment(String username, Integer pId, String body) {
		super();
		this.username = username;
		this.pId = pId;
		this.body = body;
	}



	public Comment(String username) {
		super();
		this.username = username;
	}

	public Comment(Integer pId) {
		super();
		this.pId = pId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Integer getpId() {
		return pId;
	}



	public void setpId(Integer pId) {
		this.pId = pId;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", username=" + username + ", pId=" + pId + ", body=" + body + "]";
	}
	
    
    
    
}
