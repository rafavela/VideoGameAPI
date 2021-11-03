package com.dragonfruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragonfruit.bean.YearBean;
import com.dragonfruit.repository.YearDao;

@Service("jpaService")
public class YearServiceImpl implements YearService {

	@Autowired
	private YearDao yearDao;
	
	public List<YearBean> getYearList(){
		return yearDao.findByOrderByYear();
	}
	
}
