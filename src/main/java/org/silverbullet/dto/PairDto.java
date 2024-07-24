package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class PairDto {
	@Tolerate
	public PairDto() {}
	
	private String key;
	
	private String value;
}
