package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MopSummary {
	@Tolerate
	public MopSummary() {}
	
	private Long id;
	
	private String category;
	
	private String title;
	
	private String summary;
	
	private List<String> authors;
}
