package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ImageDto {
	@Tolerate
	public ImageDto() {}
	
	private String title;
	private String image;
}
