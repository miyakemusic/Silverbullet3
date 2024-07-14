package org.silverbullet;

import java.util.List;

import org.silverbullet.entity.TestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestItemRepository extends JpaRepository<TestItemEntity, Long> {

	List<TestItemEntity> findByNode_id(Long nodeid);

}
