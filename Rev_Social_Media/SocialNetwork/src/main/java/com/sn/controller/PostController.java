package com.sn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sn.entity.Post;
import com.sn.service.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

	@Autowired
	PostService postService;
	
    @RequestMapping(value = "/addpost", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public Post addNewPost(@RequestBody Post p) {
        return this.postService.addPost(p);
    }
	
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Post> getAllPost() {
    	return this.postService.getAllPosts();
    }

    @RequestMapping(value = "/allordered", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Post> getAllPostOrdered() {
    	return this.postService.getAllPostsOrdered();
    }

    @RequestMapping(value = "/userordered", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Post> getFirst15PostsByUsernameOrdered(@RequestBody Post p) {
        return this.postService.getPostsByUsername(p.getUsername());
    }
    
    
}
