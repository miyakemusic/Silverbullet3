package org.silverbullet;

import java.util.List;

import org.silverbullet.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

	List<ProjectEntity> findByType(String type);

}
