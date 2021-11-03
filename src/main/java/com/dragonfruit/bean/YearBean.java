package com.dragonfruit.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="year")
public class YearBean {
	@Id
	@Column(name="year_id")
	public Integer yearId;
	@Column(name="year_value")	
	public Integer year;
}
