package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class NodeDetailDto {
	@Tolerate
	public NodeDetailDto() {}
	
	private Long id;
	private String name;
	private TestPointProgressDto testPointProgress;
	private List<TestItemDto> testItems;
}
