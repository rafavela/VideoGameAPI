package com.dragonfruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.repository.VideoGameDao;

@Service("jpaVideoGameService")
public class VideoGameServiceImpl implements VideoGameService {

	@Autowired
	@Qualifier("jpaVideoGameRepository")
	VideoGameDao videoGameDao;
	
	public List<VideoGameBean>  getVideoGame(){
		return videoGameDao.findAll();
	}
	
	public void saveVideoGame(VideoGameBean videoGameBean) {
		videoGameDao.save(videoGameBean);
	}
}
