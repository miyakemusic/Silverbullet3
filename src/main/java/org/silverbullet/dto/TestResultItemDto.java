package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestResultItemDto {
	@Tolerate
	public TestResultItemDto() {}
	
	private Long nodeid;
	private String key;
	private String value;
	private String status;
}
