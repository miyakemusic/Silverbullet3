package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class AlarmDto {

	public enum Type {
		no, alarm, warining
	}
	@Tolerate
	public AlarmDto() {}
	
	private Type type;
	
	private String message;
}
