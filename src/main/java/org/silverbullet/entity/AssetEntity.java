package org.silverbullet.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "asset")
public class AssetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Tolerate
	public AssetEntity() {}
	
	private String name;
	
	private String family;
	
	private String image;
	
	private String note;
	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private ManufacturerEntity manufacturer;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "asset")
	private List<RelationAssetAttributeEntity> relationAttribute = new ArrayList<>();
}
