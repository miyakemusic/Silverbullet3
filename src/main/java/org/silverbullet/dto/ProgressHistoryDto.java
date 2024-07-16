package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ProgressHistoryDto {
	@Tolerate
	public ProgressHistoryDto() {}
	
	private List<String> time;
	private List<Integer> actual;
	private List<Integer> plan;
}
