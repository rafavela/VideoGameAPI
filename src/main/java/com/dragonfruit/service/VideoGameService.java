package com.dragonfruit.service;

import java.util.List;
import java.util.Map;

import com.dragonfruit.bean.VideoGameBean;

public interface VideoGameService {

	public List<VideoGameBean>  getVideoGame(Map<String,String> allParams,int page);
	public void saveVideoGame(VideoGameBean videoGameBean);
	public void updateVideoGame(Integer videoGameId,VideoGameBean videoGameBean);	
	public void deleteVideoGame(Integer videoGameId);	
}
