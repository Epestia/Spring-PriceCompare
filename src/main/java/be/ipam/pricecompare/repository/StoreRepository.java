package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.RoleEntity;
import be.ipam.pricecompare.model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}
