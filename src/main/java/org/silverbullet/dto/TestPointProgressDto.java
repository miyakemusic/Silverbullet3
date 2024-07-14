package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestPointProgressDto {
	@Tolerate
	public TestPointProgressDto() {}
	
//	private Long id;
	private String node;
	private Long total;
	private Long pass;
	private Long fail;
	private Long rest;
}
