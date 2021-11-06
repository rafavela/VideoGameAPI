package com.dragonfruit.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="video_game")
public class VideoGameBean {
	@Id
	@Column(name="video_game_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer videoGameId;
	@Column(name="video_game_name")	
	private String name;
	private String saga;
	@ManyToOne(targetEntity=YearBean.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name = "year_id", referencedColumnName = "year_id")	
	private YearBean yearBean;
	@Column(name="is_finished")		
	private Boolean isFinished;
	@Column(name="path_name")			
	private String cover;
}
