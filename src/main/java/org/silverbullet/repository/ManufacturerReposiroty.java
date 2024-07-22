package org.silverbullet.repository;

import org.silverbullet.entity.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerReposiroty extends JpaRepository<ManufacturerEntity, Long> {

	ManufacturerEntity findByName(String manufacturer);

}
