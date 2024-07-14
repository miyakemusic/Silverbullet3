package org.silverbullet.dto;

import org.silverbullet.dto.ProgressDto.Type;

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
