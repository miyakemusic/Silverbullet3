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
	
	private String image;

}
