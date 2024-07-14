package org.silverbullet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "testitem")
public class TestItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Tolerate
	public TestItemEntity() {}
	
	private String name;
	
	@ManyToOne
	 @JoinColumn(name = "node_id")
	private NodeEntity node;
	
	@Enumerated(EnumType.STRING)
	private TestStatus status;
}