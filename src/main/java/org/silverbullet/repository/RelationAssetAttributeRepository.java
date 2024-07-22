package org.silverbullet.repository;

import org.silverbullet.entity.RelationAssetAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface RelationAssetAttributeRepository extends JpaRepository<RelationAssetAttributeEntity, Long> {
	@Transactional
	void deleteByAsset_id(Long id);

}
