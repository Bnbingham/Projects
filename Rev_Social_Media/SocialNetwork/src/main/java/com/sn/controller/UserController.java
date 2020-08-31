package com.sn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sn.beans.ChangePass;
import com.sn.entity.User;
import com.sn.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    
    @RequestMapping(value = "/adduser", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }
    
    @RequestMapping(value = "/findbyusername", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public User findUserByUsername(@RequestBody User user) {
    	return this.userService.findUserByusername(user.getUsername());
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public User loginUser(@RequestBody User user) {
    	if(this.userService.existsByUsernameAndPassword(user.getUsername(), user.getPassword()) == false) {
    		return null;
    	} else {
    		return this.userService.findUserByusername(user.getUsername());
    	}
    	
    }
    
    @RequestMapping(value = "/changepass", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public User loginUser(@RequestBody ChangePass cp) {
    	if(this.userService.existsByUsernameAndPassword(cp.getUsername(), cp.getPasswordOld()) == false) {
    		return null;
    	} else {
    		return this.userService.updatePassword(cp.getUsername(), cp.getPasswordOld(), cp.getPasswordNew());
    	}
    	
    }
    
    @PostMapping("/updateprofilepic")
    public List<byte[]> uploadProfilePic(@RequestParam("imageFile") MultipartFile file) throws IOException {
    	List<byte[]> rtrn = new ArrayList<byte[]>();
    	String s = file.getOriginalFilename();
    	String username = s.replace(".png", "");
    	System.out.println(username);
    	User user = this.userService.findUserByusername(username);
        user.setPicLink(file.getBytes());
        this.userService.addUser(user);
        rtrn.add(file.getBytes());
        return rtrn;
    }    

    @RequestMapping(value = "/getprofilepic", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public List<byte[]> getProfilePic(@RequestBody User user) {
    	return this.userService.getProfilePic(user.getUsername());
    }

    @RequestMapping(value = "/usersearch", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> searchForUser(@RequestBody User user) {
    	return this.userService.findUserByUsernameContaining(user.getUsername());
    }

}
