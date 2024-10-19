package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TestResultRawDataDto {
	@Tolerate
	public TestResultRawDataDto() {}
	
	private double[] x;
	private double[] y;
	
	private List<TestResultRawDataItemDto> parameters;
}
