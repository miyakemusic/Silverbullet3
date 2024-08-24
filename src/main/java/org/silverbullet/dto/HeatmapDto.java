package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class HeatmapDto {
	@Tolerate
	public HeatmapDto() {}
	
	private LatLngDto center;
	
	private List<HeatmapElementDto> heatmap;
}
