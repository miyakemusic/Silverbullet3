package org.silverbullet.entity.trash;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "mop_line")
public class MopLineEntity {
	
	@Tolerate
	public MopLineEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String operation;
	
	private String target;
	
	private String targetImage;
	
	private String tool;
	
	private String toolImage;
	
	private List<String> criteria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mopsection_id")
	private MopSectionEntity mopSection;
}
