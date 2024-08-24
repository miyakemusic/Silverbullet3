package org.silverbullet.entity;

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
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;


@Builder
@Data
@Entity
@Table(name = "project_node")
public class NodeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Tolerate
	public NodeEntity() {}
	
	private String name;
	
	private String type;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentNode", cascade = CascadeType.ALL)
	@OrderBy("name ASC")
	private List<NodeEntity> children;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "node", cascade = CascadeType.ALL)
//	private List<TestItemEntity> testItems;
	
	@ManyToOne
	@JoinColumn(name = "parentnode_id")
	private NodeEntity parentNode;

	@ManyToOne
	@JoinColumn(name = "mop_id")
	private MopEntity mop;
}
