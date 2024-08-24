package org.silverbullet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.TestItemDto;
import org.silverbullet.entity.TestItemEntity;

public class TestItemMapper {

	public TestItemDto toDto(TestItemEntity testItem) {
		return TestItemDto.builder()
				.criteria(testItem.getCriteria())
			//	.key(testItem.getKey())
				.name(testItem.getName())
				.node(testItem.getNode() != null ? testItem.getNode().getName() : "")
				.result(testItem.getResult())
				.status(testItem.getStatus().toString())
				.tool(testItem.getTool())
				.build();
	}
	public List<TestItemDto> toDto(List<TestItemEntity> testItems) {
		return testItems.stream().map(ti -> toDto(ti)).collect(Collectors.toList());
	}

}
