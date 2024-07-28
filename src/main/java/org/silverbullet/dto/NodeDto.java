package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class NodeDto {
	@Tolerate
	public NodeDto() {}
	
	private String name;
	private String type;
}
