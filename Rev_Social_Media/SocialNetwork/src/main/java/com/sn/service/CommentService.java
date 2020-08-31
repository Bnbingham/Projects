package com.sn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sn.dao.CommentDAO;
import com.sn.entity.Comment;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentdao;
	
	public List<Comment> getAllComments() {
		return this.commentdao.findAll();
	}

	public Comment addComment(Comment c) {
		return this.commentdao.save(c);
	}
	
}
