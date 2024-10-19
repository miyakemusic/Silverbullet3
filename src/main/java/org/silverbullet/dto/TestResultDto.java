package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestResultDto {
	@Tolerate
	public TestResultDto() {}
	
	private Long testerid;
	private List<TestResultItemDto> items;
	private TestResultRawDataDto rawData;
}
