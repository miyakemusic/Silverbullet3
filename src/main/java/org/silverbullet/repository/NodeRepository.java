package org.silverbullet.repository;
import java.util.List;
import java.util.Optional;

import org.silverbullet.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Long>{

	Optional<NodeEntity> findByName(String string);

	List<NodeEntity> findByType(String string);

	List<NodeEntity> findByMop_id(Long id);

}
