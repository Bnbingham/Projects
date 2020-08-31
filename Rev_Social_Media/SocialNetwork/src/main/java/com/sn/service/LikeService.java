package com.sn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sn.dao.LikesDAO;
import com.sn.entity.Likes;

@Service
public class LikeService {
	
	@Autowired
	LikesDAO likesdao;
	
	public Likes addLike(Likes l) {
		return this.likesdao.save(l);
	}
	
    public List<Likes> getAllLikes() {
        return this.likesdao.findAll();
    }

    public List<Integer> findLikesByPIdAndUsername(int pId, String username) {
    	List<Integer> l = new ArrayList<Integer>();
    	if(this.likesdao.findLikesBypIdAndUsername(pId, username) == null) {
    		l.add(0);
    		return l;
    	} else {
    		l.add(1);
    		return l;
    	}
    }
    
    public List<Likes> findLikesByPid(int pId) {
    	return this.likesdao.findLikesBypId(pId);
    }
    
    public List<String> deleteLikesByPIdAndUsername(int pId, String username) {
    	List<String> list = new ArrayList<String>();
    	Likes l = this.likesdao.findLikesBypIdAndUsername(pId, username);
    	if(l == null) {
    		list.add("No like found");
    		return list;
    	} else {
    		list.add("Like removed");
    		this.likesdao.delete(l);
    		return list;
    	}
    }
}
