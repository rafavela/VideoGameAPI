package com.dragonfruit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.bean.YearBean;
import com.dragonfruit.repository.PageableVideoGameDao;
import com.dragonfruit.repository.VideoGameDao;
import com.dragonfruit.repository.YearDao;
import com.dragonfruit.util.VideoGameConstants;

@Service("jpaVideoGameService")
public class VideoGameServiceImpl implements VideoGameService {

	@Autowired
	@Qualifier("jpaVideoGameRepository")
	private VideoGameDao videoGameDao;
	
	@Autowired
	@Qualifier("jpaYearRepository")
	private YearDao yearDao;	
	
	@Autowired
	@Qualifier("jpaPageableRepository")
	private PageableVideoGameDao pageableVideoGameDao;
	
	public List<VideoGameBean>  getVideoGame(Map<String,String> allParams,int page){
		String name=allParams.containsKey(VideoGameConstants.NAME)?
				allParams.get(VideoGameConstants.NAME):"";
		String saga=allParams.containsKey(VideoGameConstants.SAGA)?
				allParams.get(VideoGameConstants.SAGA):"";		
		YearBean yearBean=this.getYear(allParams);
		boolean isNamePresent=StringUtils.hasText(name);
		boolean isSagaPresent=StringUtils.hasText(saga);
		boolean isYearPresent=yearBean.getYearId()!=0;	
		List<VideoGameBean> videoGameBeanList=new ArrayList<>();
		Pageable pageable = PageRequest.of(page, 2);
		
		if(isNamePresent && isSagaPresent && isYearPresent){
			videoGameBeanList=pageableVideoGameDao.findByNameAndSagaAndYearBean(name, saga, yearBean, pageable);
		}else if(isNamePresent && isSagaPresent) {
			videoGameBeanList=pageableVideoGameDao.findByNameAndSaga(name, saga, pageable);
		}else if(isNamePresent && isYearPresent) {
			videoGameBeanList=pageableVideoGameDao.findByNameAndYearBean(name, yearBean, pageable);
		}else if(isSagaPresent && isYearPresent) {
			videoGameBeanList=pageableVideoGameDao.findBySagaAndYearBean(saga, yearBean, pageable);
		}else if(isNamePresent) {
			videoGameBeanList=pageableVideoGameDao.findByName(name, pageable);
		}else if(isSagaPresent) {
			videoGameBeanList=pageableVideoGameDao.findBySaga(saga, pageable);
		}else if(isYearPresent) {
			videoGameBeanList=pageableVideoGameDao.findByYearBean(yearBean, pageable);
		}

		return videoGameBeanList;
	}
	
	private YearBean getYear(Map<String,String> allParams) {
		String year=allParams.containsKey(VideoGameConstants.YEAR)?
				allParams.get(VideoGameConstants.YEAR):"0";
		Integer yearValue=Integer.valueOf(year);
		
		return Optional.ofNullable(yearDao.findByYear(yearValue))
				.orElse(this.returnEmptyYear());
	}
	
	private YearBean returnEmptyYear() {
		YearBean yearBean=new YearBean();
		
		yearBean.setYear(0);
		yearBean.setYearId(0);
		
		return yearBean;
		
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
