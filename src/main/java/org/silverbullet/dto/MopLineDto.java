package org.silverbullet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class MopLineDto {
	@Tolerate
	public MopLineDto() {}
	
	private String key;
	
//	private Integer number;
	
	private String operation;
	
	private ImageDto target;
	
	private ImageDto tool;
	
	private CriteriaDto criteria;
	
	private String result;
	
	private String status;
//	private String target;
//	
//	private String targetImage;
//	
//	private String tool;
//	
//	private String toolImage;

}
