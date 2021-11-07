package com.dragonfruit.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dragonfruit.bean.VideoGameBean;
import com.dragonfruit.bean.YearBean;

@Repository("jpaPageableRepository")
public interface PageableVideoGameDao extends PagingAndSortingRepository<VideoGameBean, Integer>{
	public List<VideoGameBean> findByNameAndSagaAndYearBean(String name,String saga,YearBean yearBean
			,Pageable pageable);
	public List<VideoGameBean> findByNameAndSaga(String name,String saga,Pageable pageable);
	public List<VideoGameBean> findByNameAndYearBean(String name,YearBean yearBean,Pageable pageable);
	public List<VideoGameBean> findBySagaAndYearBean(String saga,YearBean yearBean,Pageable pageable);
	public List<VideoGameBean> findByName(String name,Pageable pageable);
	public List<VideoGameBean> findBySaga(String saga,Pageable pageable);	
	public List<VideoGameBean> findByYearBean(YearBean yearBean, Pageable pageable);	
}
