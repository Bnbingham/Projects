package com.sn.entity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="POST_TABLE")
public class Post {

	
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_PK_SEQ")
    private Integer id;
  
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "POST_BODY")
    private String post;
    
    @Column(name = "POST_IMAGE", nullable = true, columnDefinition="BLOB")
    private byte[] image;
    
    @Column(name = "POST_TIME")
    private java.sql.Timestamp date;
    
    @Column(name = "IS_LIKED", columnDefinition="integer default 0")
    private int isLiked;
    
    @Column(name = "LIKE_COUNT", columnDefinition="integer default 0")
    private int likeCount;
    
    @PrePersist
    protected void prePersist() {
        if (this.date == null) {
        	long millis = System.currentTimeMillis();
        	java.sql.Timestamp ts = new java.sql.Timestamp(millis);
        	this.date = ts;
        }
    }

	public Post() {
		super();
	}

	public Post(String username, String post, byte[] image) {
		super();
		this.username = username;
		this.post = post;
		this.image = image;
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", username=" + username + ", post=" + post + ", date=" + date + ", isLiked="
				+ isLiked + ", likeCount=" + likeCount + "]";
	}

	
    
}
