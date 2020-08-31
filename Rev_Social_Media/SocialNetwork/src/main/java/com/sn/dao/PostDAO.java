package com.sn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sn.entity.Post;

@Repository
public interface PostDAO extends JpaRepository<Post, Integer> {
	
	public List<Post> findByOrderByDateDesc();
	public List<Post> findFirst15ByUsernameOrderByDate(String username);
	
}
