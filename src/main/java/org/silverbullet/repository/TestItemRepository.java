package org.silverbullet.repository;

import java.util.List;

import org.silverbullet.entity.TestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestItemRepository extends JpaRepository<TestItemEntity, Long> {

	List<TestItemEntity> findByNode_id(Long nodeid);

	@Transactional
	void deleteByNode_id(Long nodeid);

}
