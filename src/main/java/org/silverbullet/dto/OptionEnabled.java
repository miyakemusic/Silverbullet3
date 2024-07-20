package org.silverbullet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class OptionEnabled {
	@Tolerate
	public OptionEnabled() {}
	private String title;
	private Boolean enabled = true;
}
