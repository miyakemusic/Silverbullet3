package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class HeatmapElementDto {
	@Tolerate
	public HeatmapElementDto() {}
	
	private LatLngDto location;
	private double weight;
}
