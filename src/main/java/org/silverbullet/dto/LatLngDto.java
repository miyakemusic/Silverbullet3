package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class LatLngDto {
	@Tolerate
	public LatLngDto() {}
	
	private double lat;
	private double lng;
}
