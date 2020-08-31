package com.sn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sn.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
