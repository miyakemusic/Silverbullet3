package org.silverbullet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class MopSection {
	@Tolerate
	public MopSection() {}
	
	private String title;
	
	private String image;
	
	private List<MopLineDto> lines = new ArrayList<>();
}
