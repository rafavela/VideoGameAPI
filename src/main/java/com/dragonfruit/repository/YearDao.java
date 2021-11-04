package com.dragonfruit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dragonfruit.bean.YearBean;

@Repository("jpaYearRepository")
public interface YearDao extends CrudRepository<YearBean, Integer> {
	public List<YearBean> findByOrderByYear();
}
