package com.dragonfruit.service;

import java.util.List;

import com.dragonfruit.bean.VideoGameBean;

public interface VideoGameService {

	public List<VideoGameBean>  getVideoGame();
	public void saveVideoGame(VideoGameBean videoGameBean);
	public void updateVideoGame(Integer videoGameId,VideoGameBean videoGameBean);	
	public void deleteVideoGame(Integer videoGameId);	
}
