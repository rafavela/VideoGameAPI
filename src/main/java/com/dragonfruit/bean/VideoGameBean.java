package com.dragonfruit.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="video_game")
public class VideoGameBean {
	@Id
	@Column(name="video_game_id")
	public Integer videoGameId;
	@Column(name="video_game_name")	
	public String name;
	public String saga;
	@ManyToOne(targetEntity=YearBean.class)
	@JoinColumn(name = "year_id", referencedColumnName = "year_id")	
	public YearBean yearBean;
	@Column(name="is_finished")		
	public Boolean isFinished;
	@Column(name="path_name")			
	public String cover;
}
