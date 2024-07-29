package org.silverbullet.entity;

import java.util.List;

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
//	private String title;
//	private String category;
//	private String image;
//	private String description;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mop", cascade = CascadeType.ALL)
//	private List<MopSectionEntity> sections;
//	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mop", cascade = CascadeType.ALL)
//	private List<RelationMopUserEntity> relationMopUser;
}
