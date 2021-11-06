package com.dragonfruit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.bean.YearBean;
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
	
	public List<VideoGameBean>  getVideoGame(Map<String,String> allParams){
		String name=allParams.containsKey(VideoGameConstants.NAME)?
				allParams.get(VideoGameConstants.NAME):"";
		String saga=allParams.containsKey(VideoGameConstants.SAGA)?
				allParams.get(VideoGameConstants.SAGA):"";		
		YearBean yearBean=this.getYear(allParams);
		boolean isNamePresent=StringUtils.hasText(name);
		boolean isSagaPresent=StringUtils.hasText(saga);
		boolean isYearPresent=yearBean.getYearId()!=0;	
		List<VideoGameBean> videoGameBeanList=new ArrayList<>();
		System.out.println("before isNamePresent -->"+isNamePresent+"<--");
		
		if(isNamePresent && isSagaPresent && isYearPresent){
			System.out.println(" 1 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findByNameAndSagaAndYearBean(name, saga, yearBean);
		}else if(isNamePresent && isSagaPresent) {
			System.out.println(" 2 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findByNameAndSaga(name, saga);
		}else if(isNamePresent && isYearPresent) {
			System.out.println(" 3 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findByNameAndYearBean(name, yearBean);
		}else if(isSagaPresent && isYearPresent) {
			System.out.println(" 4 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findBySagaAndYearBean(saga, yearBean);
		}else if(isNamePresent) {
			System.out.println(" NAME -->"+name+"<--");
			videoGameBeanList=videoGameDao.findByName(name);
		}else if(isSagaPresent) {
			System.out.println(" 5 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findBySaga(saga);
		}else if(isYearPresent) {
			System.out.println(" 6 -->"+name+"<--");
			videoGameBeanList=videoGameDao.findByYearBean(yearBean);
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
