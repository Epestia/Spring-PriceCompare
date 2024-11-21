package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    // Utilisez le bon nom d'attribut dans la méthode
    List<PriceEntity> findByStore_StoreId(Long storeId);
}
