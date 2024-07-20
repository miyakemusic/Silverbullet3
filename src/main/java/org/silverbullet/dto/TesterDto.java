package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class TesterDto {
	@Tolerate
	public TesterDto() {}
	
	private String categories;
	private String manufacturer;
	private String family;	
	private String model;	
	private String image;
	private String serialNumber;
	private String currentUser;
}
