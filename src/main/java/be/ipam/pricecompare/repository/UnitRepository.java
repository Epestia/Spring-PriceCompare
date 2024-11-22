package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
}
