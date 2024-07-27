package org.silverbullet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserDto {
	@Tolerate
	public UserDto() {}
	
	private Long id;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
}
