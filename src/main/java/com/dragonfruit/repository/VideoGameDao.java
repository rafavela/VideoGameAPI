package com.dragonfruit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dragonfruit.bean.VideoGameBean;

@Repository("jpaVideoGameRepository")
public interface VideoGameDao extends CrudRepository<VideoGameBean, Integer> {
	public List<VideoGameBean> findAll();
	public VideoGameBean findByVideoGameId(Integer videoGameId);
}
