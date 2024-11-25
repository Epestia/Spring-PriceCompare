package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.ExportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportRepository extends JpaRepository<ExportEntity, Long> {
}
