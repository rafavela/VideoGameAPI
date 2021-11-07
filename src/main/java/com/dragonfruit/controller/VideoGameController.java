package com.dragonfruit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.service.VideoGameService;
import com.dragonfruit.util.VideoGameConstants;

@RestController
@RequestMapping(path="/videogame",produces="application/json")
public class VideoGameController {

	@Autowired
	@Qualifier("jpaVideoGameService")
	private VideoGameService videoGameService;
	
	@GetMapping
	public List<VideoGameBean> getVideoGameBean(@RequestParam Map<String,String> allParams){
		int page=this.getPage(allParams);
		
		return videoGameService.getVideoGame(allParams, page);
	}	

	private int getPage(Map<String,String> allParams) {
		return StringUtils.hasText(allParams.get(VideoGameConstants.PAGE))?
				Integer.valueOf(allParams.get(VideoGameConstants.PAGE)):0;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void postVideoGameBean(@RequestBody VideoGameBean videoGameBean){
		videoGameService.saveVideoGame(videoGameBean);
	}		
	
	@PutMapping(path="/{videoGameId}", consumes="application/json")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void putVideoGameBean(@PathVariable("videoGameId") Integer videoGameId
			, @RequestBody VideoGameBean videoGameBean){
		videoGameService.updateVideoGame(videoGameId, videoGameBean);
	}
	
	@DeleteMapping (path="/{videoGameId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVideoGame(@PathVariable("videoGameId") Integer videoGameId){
		videoGameService.deleteVideoGame(videoGameId);
	}	
	
}
