package org.silverbullet.entity.trash;

import java.util.ArrayList;
import java.util.List;

import org.silverbullet.entity.MopEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
@Entity
@Table(name = "mop_section")
public class MopSectionEntity {
	@Tolerate
	public MopSectionEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private List<String> images;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mop_id")
	private MopEntity mop;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="mopSection")
	private List<MopLineEntity> lines = new ArrayList<>();
}
