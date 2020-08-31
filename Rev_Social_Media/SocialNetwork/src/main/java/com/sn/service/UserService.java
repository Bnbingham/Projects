package com.sn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sn.dao.UserDAO;
import com.sn.entity.User;

@Service
 public class UserService {
     @Autowired
     UserDAO userDao;
 
     public List<User> getAllUsers() {
         return this.userDao.findAll();
     }
 
     public User addUser(User user) {
         return this.userDao.save(user);
     }
 
     
     public User findUserByusername(String username) {
    	 return this.userDao.findUserByUsername(username);
     }
     
     public boolean existsByUsernameAndPassword(String username, String password) {
    	 return this.userDao.existsByUsernameAndPassword(username, password);
     }
 
     public User updatePassword(String username, String passwordOld, String passwordNew) {
    	 User u = this.userDao.findUserByUsername(username);
    	 u.setPassword(passwordNew);
    	 this.userDao.save(u);
    	 return u;
     }     

     public List<byte[]> getProfilePic(String username) {
    	 List<byte[]> b = new ArrayList<byte[]>();
    	 User user = this.userDao.findUserByUsername(username);
    	 b.add(user.getPicLink());
    	 return b;
     }
    
     public List<User> findUserByUsernameContaining(String search) {
    	 return this.userDao.findUserByUsernameContaining(search);
     }
     
     
     
}
