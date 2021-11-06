package com.dragonfruit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	public void updateVideoGame(Integer videoGameId,VideoGameBean videoGameBean) {
		VideoGameBean storedVideoGameBean = videoGameDao.findByVideoGameId(videoGameId);

		if(StringUtils.hasText(videoGameBean.getName())) {
			storedVideoGameBean.setName(videoGameBean.getName());
		}
		if(StringUtils.hasText(videoGameBean.getSaga())) {
			storedVideoGameBean.setSaga(videoGameBean.getSaga());
		}
		if(StringUtils.hasText(videoGameBean.getCover())) {
			storedVideoGameBean.setCover(videoGameBean.getCover());
		}	
		if(!Optional.ofNullable(videoGameBean.getIsFinished())
				.isEmpty()) {
			storedVideoGameBean.setIsFinished(videoGameBean.getIsFinished());
		}
		if(!Optional.ofNullable(videoGameBean.getYearBean())
				.isEmpty()) {
			storedVideoGameBean.setYearBean(videoGameBean.getYearBean());
		}		
		videoGameDao.save(storedVideoGameBean);
	}	
	
	public void deleteVideoGame(Integer videoGameId) {
		VideoGameBean storedVideoGameBean = videoGameDao.findByVideoGameId(videoGameId);
		
		videoGameDao.delete(storedVideoGameBean);
	}		
}
