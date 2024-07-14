package org.silverbullet.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ScheduleDto {
	@Tolerate
	public ScheduleDto() {}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//"yyyy-MM-dd HH:mm a z")
	private Date start;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//"yyyy-MM-dd HH:mm a z")
	private Date deadline;
}
