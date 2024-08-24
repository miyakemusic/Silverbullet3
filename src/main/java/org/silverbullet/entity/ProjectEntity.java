package org.silverbullet.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Tolerate
	public ProjectEntity() {}
	
	private String name;
	
	private String type;
	
	private String description;
	
	private String area;
	
	private Date start;
	
	private Date deadline;
	
	private String cost_unit;
	
	private int budget;
	
	private int expenditure;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "node_id")
	private NodeEntity node;
}
