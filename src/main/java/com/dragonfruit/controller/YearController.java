package com.dragonfruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragonfruit.bean.YearBean;
import com.dragonfruit.service.YearService;

@RestController
@RequestMapping(path="/year",produces="application/json")
public class YearController {
	
	@Autowired
	@Qualifier("jpaService")
	private YearService yearService;
	
	@GetMapping
	public List<YearBean> getYearBean(){
		return yearService.getYearList();
	}
	
}
