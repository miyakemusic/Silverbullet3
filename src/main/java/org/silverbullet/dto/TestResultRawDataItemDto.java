package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestResultRawDataItemDto {
	@Tolerate
	public TestResultRawDataItemDto() {}
	
	private String title;
	private String value;
	
}
