package org.silverbullet.mapper;

import org.silverbullet.dto.AlarmDto;
import org.silverbullet.dto.CostDto;
import org.silverbullet.dto.ProgressDto;
import org.silverbullet.dto.ProjectDto;
import org.silverbullet.dto.ScheduleDto;
import org.silverbullet.entity.ProjectEntity;

public class ProjectMapper {

	public ProjectDto toDto(ProjectEntity p) {
		return ProjectDto.builder()
		.id(p.getId())
		.type(p.getType())
		.name(p.getName())
		.description(p.getDescription())
		.area(p.getArea())
		.schedule(ScheduleDto.builder().start(p.getStart()).deadline(p.getDeadline()).build())
		.progress(ProgressDto.builder().type(ProgressDto.Type.ontime).days(0).build())
		.cost(CostDto.builder().budget(p.getBudget()).expenditure(p.getExpenditure()).unit("USD").build())
		.alarm(AlarmDto.builder().type(AlarmDto.Type.alarm).message("Throughput").build())
		.build();
		
	}

}
