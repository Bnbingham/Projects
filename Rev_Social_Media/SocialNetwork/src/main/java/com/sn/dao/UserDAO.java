package com.sn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sn.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	
	public User findUserByUsername(String username);
	public boolean existsByUsernameAndPassword(String username, String password);
	public List<User> findUserByUsernameContaining(String search);
}
