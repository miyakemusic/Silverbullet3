package org.silverbullet.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.MopDto;
import org.silverbullet.entity.TestItemEntity;
import org.silverbullet.entity.TestStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestItemConverter {

	public List<TestItemEntity> testItems(String json) {
		try {
			MopDto dto = new ObjectMapper().readValue(json, MopDto.class);
			return dto.getSections().stream().flatMap(sec -> sec.getLines().stream()).map(line -> TestItemEntity.builder()
					.name(line.getTarget() != null ? line.getTarget().getTitle() : "")
		//			.key(line.getKey())
					.status(TestStatus.NOT_YET)
					.criteria(line.getCriteria() != null ? line.getCriteria().getCriteria().toString() : "")
					.result("--")
					.tool(line.getTool() != null ? line.getTool().getTitle() : "")
					.build()).collect(Collectors.toList());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
