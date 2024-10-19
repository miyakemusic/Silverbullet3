package org.silverbullet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
@Entity
@Table(name = "test_result")
public class TestResultEntity {
	@Tolerate
	public TestResultEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long nodeid;
	private String mopItemKey;
	
	@Column(length=10485760)
	private String json;
	
	private String status;
}
