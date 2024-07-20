package org.silverbullet.dto;

import java.util.List;

import org.silverbullet.dto.AlarmDto.Type;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class AssetFilterDto {
	@Tolerate
	public AssetFilterDto() {}
	
	private String title;
	
	private List<OptionEnabled> options;
	
	private Boolean text;
}
