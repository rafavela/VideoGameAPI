package com.dragonfruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.service.VideoGameService;

@RestController
@RequestMapping(path="/videogame",produces="application/json")
public class VideoGameController {

	@Autowired
	@Qualifier("jpaVideoGameService")
	private VideoGameService videoGameService;
	
	@GetMapping
	public List<VideoGameBean> getVideoGameBean(){
		return videoGameService.getVideoGame();
	}	

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void getPostVideoGameBean(@RequestBody VideoGameBean videoGameBean){
		videoGameService.saveVideoGame(videoGameBean);
	}		
	
}
