package com.sn.controller;

import java.util.ArrayList;
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

import com.sn.entity.Likes;
import com.sn.service.LikeService;

@RestController
@RequestMapping("/likes")
@CrossOrigin
public class LikesController {

	@Autowired
	LikeService likeservice;
	
    @RequestMapping(value = "/addlike", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public Likes addNewLike(@RequestBody Likes l) {
        return this.likeservice.addLike(l);
    }
	
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Likes> getAllLikes() {
    	return this.likeservice.getAllLikes();
    }
	
    @RequestMapping(value = "/isliked", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Integer> isLiked(@RequestBody Likes l) {
    	return this.likeservice.findLikesByPIdAndUsername(l.getpId(), l.getUsername());
    }
    
    @RequestMapping(value = "/likecount", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Integer> getLikeCount(@RequestBody Likes l) {
    	List<Likes> likes =  this.likeservice.findLikesByPid(l.getpId());
    	int i = likes.size();
    	List<Integer> intlist = new ArrayList<Integer>();
    	intlist.add(i);
    	return intlist;
    }
    
    @RequestMapping(value = "/deletelike", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<String> deleteLike(@RequestBody Likes l) {
    	return this.likeservice.deleteLikesByPIdAndUsername(l.getpId(), l.getUsername());
    }
    
}
