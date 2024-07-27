package org.silverbullet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MopSectionDto {
	@Tolerate
	public MopSectionDto() {}
	
	private String title;
	
	private List<String> images;
	
	private List<MopLineDto> lines = new ArrayList<>();
}
