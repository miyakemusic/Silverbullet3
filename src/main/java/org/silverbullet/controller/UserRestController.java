package org.silverbullet.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.UserDto;
import org.silverbullet.entity.UserEntity;
import org.silverbullet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/init")
	public String init(Principal principal) {
		userRepository.save(UserEntity.builder().username("dr.dre@deathrow.com").firstName("Andre").lastName("Young").build());
		userRepository.save(UserEntity.builder().username("tupac@deathrow.com").firstName("Tupac").lastName("Shakur").build());
		userRepository.save(UserEntity.builder().username("snoopdoggydogg@deathrow.com").firstName("Calvin").lastName("Broadus").build());
		return "OK";
	}
	
	@GetMapping("/users")
	public List<UserDto> users(Principal principal) {
		List<UserDto> ret = this.userRepository.findAll().stream().map(u -> UserDto.builder().id(u.getId()).username(u.getUsername()).firstName(u.getFirstName()).lastName(u.getLastName()).build()).collect(Collectors.toList());
		return ret;
	}
	
	@DeleteMapping("/debug/delete")
	public String delete(Principal principal) {
		userRepository.deleteAll();
		return "OK";
	}
}
