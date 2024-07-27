package org.silverbullet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class CriteriaDto {
	@Tolerate
	public CriteriaDto() {}
	
	private List<String> criteria = new ArrayList<>();
	
}
