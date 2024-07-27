package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MopLineDto {
	@Tolerate
	public MopLineDto() {}
	
	private Integer number;
	
	private String operation;
	
	private ImageDto target;
	
	private ImageDto tool;
	
	private CriteriaDto criteria;
//	private String target;
//	
//	private String targetImage;
//	
//	private String tool;
//	
//	private String toolImage;

}
