package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class CostDto {
	@Tolerate
	public CostDto() {}
	private String unit; //$
	private int budget;
	private int expenditure;
}
