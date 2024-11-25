package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
