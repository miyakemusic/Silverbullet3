package org.silverbullet.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class ProjectSummaryDto {
	
	@Tolerate
	public ProjectSummaryDto() {}
	
	private Long id;
    private String name;//: 'FTTH#001',
	private String description;//: 'FTTH Construction',
	private String location;//: 'Tokyo Kita Jujo Area',
    private ScheduleDto schedule;//: {start: '2024/4/1', complete: '2024/5/31'},
	private ProgressDto progress;//: {type: 'delay', days: '3'},
	private CostDto  cost;// : {unit: '$', budget: 32000, used: 23000},
	private AlarmDto alarm;
}
