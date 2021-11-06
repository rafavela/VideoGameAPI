package com.dragonfruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.bean.YearBean;

@Repository("jpaVideoGameRepository")
public interface VideoGameDao extends CrudRepository<VideoGameBean, Integer> {
	public List<VideoGameBean> findByNameAndSagaAndYearBean(String name,String saga,YearBean yearBean);
	public List<VideoGameBean> findByNameAndSaga(String name,String saga);
	public List<VideoGameBean> findByNameAndYearBean(String name,YearBean yearBean);
	public List<VideoGameBean> findBySagaAndYearBean(String saga,YearBean yearBean);
	public List<VideoGameBean> findByName(String name);
	public List<VideoGameBean> findBySaga(String saga);
	public List<VideoGameBean> findByYearBean(YearBean yearBean);
	public VideoGameBean findByVideoGameId(Integer videoGameId);
}
