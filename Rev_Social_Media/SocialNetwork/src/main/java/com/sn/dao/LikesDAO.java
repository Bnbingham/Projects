package com.sn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sn.entity.Likes;

@Repository
public interface LikesDAO extends JpaRepository<Likes, Integer>{
	
	public List<Likes> findLikesBypId(int pId);
	public Likes findLikesBypIdAndUsername(int pId, String username);

}
