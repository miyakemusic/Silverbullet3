package org.silverbullet.repository;

import java.util.List;

import org.silverbullet.entity.TestResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResultEntity, Long> {
//	List<TestResultEntity> findByNodeid(Long nodeid);
//	List<TestResultEntity> findByMopItemKey(String mop_item_key);
	List<TestResultEntity> findByNodeidAndMopItemKey(Long nodeid, String mop_item_key);

}
