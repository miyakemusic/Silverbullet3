package org.silverbullet.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ProjectDto {
	
	@Tolerate
	public ProjectDto() {}
	
	private Long id;
    private String name;//: 'FTTH#001',
    private String type;
	private String description;//: 'FTTH Construction',
	private String area;//: 'Tokyo Kita Jujo Area',
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private ScheduleDto schedule;//: {start: '2024/4/1', complete: '2024/5/31'},
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProgressDto progress;//: {type: 'delay', days: '3'},
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CostDto  cost;// : {unit: '$', budget: 32000, used: 23000},
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AlarmDto alarm;
}
