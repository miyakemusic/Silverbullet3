package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestItemDto {
	@Tolerate
	public TestItemDto() {}
	
	private Long id;
	
	private String node;
	
	private String name;
	
	private String status;
}
