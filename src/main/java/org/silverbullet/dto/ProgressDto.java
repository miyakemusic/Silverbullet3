package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ProgressDto {
	public enum Type {
		ontime,
		delay,
		ahead
	}
	
	@Tolerate
	public ProgressDto() {}
	private Type type;
	private int days;
}
