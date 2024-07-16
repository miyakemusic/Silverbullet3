package org.silverbullet.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//"yyyy-MM-dd HH:mm a z")
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//"yyyy-MM-dd HH:mm a z")
	private Date deadline;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//"yyyy-MM-dd HH:mm a z")
	private Date estimatedCompletionDate;

}
