package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TesterDto {
	@Tolerate
	public TesterDto() {}
	
	private String manufacturer;
	
	private String model;
	
	private String family;
	
	private String url;
}
