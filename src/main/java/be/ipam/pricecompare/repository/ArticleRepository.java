package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findByArticleName(String articleName);

    Optional<ArticleEntity> findByArticleDefaultPrice(Double price);

    Optional<ArticleEntity> findByArticleNameAndUnit_UnitName(String articleName, String unitName);
}
