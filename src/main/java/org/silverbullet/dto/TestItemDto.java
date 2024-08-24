package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestItemDto {
	@Tolerate
	public TestItemDto() {}
	
	private String key;
	
	private String node;
	
	private String name;
	
	private String status;
	
	private String criteria;
	
	private String tool;
	
	private String result;
}
