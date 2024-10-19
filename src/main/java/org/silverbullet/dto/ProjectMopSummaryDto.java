package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ProjectMopSummaryDto {

	@Tolerate
	public ProjectMopSummaryDto() {
		
	}
	
	private List<String> path;
	
	private Long nodeid;
	private Long mopid;
	private String name;
}
