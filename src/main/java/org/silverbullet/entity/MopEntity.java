package org.silverbullet.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.MopDto;
import org.silverbullet.dto.TestItemDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
@Entity
@Table(name = "mop")
public class MopEntity {
	
	@Tolerate
	public MopEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(length=10485760)
	private String json;
}
