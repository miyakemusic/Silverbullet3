package org.silverbullet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MopDto {
	@Tolerate
	public MopDto() {}
	
	private Long id;
	private String title;
	private String image;
	private String description;
	private List<MopSection> sections;
	
}
