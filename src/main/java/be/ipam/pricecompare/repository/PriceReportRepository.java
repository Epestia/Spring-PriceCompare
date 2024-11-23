package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.PriceReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceReportRepository extends JpaRepository<PriceReportEntity, Long> {


    List<PriceReportEntity> findByArticle_ArticleId(Long articleId);


    List<PriceReportEntity> findByStore_StoreId(Long storeId);

    List<PriceReportEntity> findByIsValidated(Boolean isValidated);
}

