package be.ipam.pricecompare.repository;

import be.ipam.pricecompare.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    // Méthode pour trouver un article par son nom
    Optional<ArticleEntity> findByArticleName(String articleName);

    // Méthode pour trouver un article par son prix
    Optional<ArticleEntity> findByArticleDefaultPrice(Double price);

    // Méthode pour trouver un article par son nom et son unité
    Optional<ArticleEntity> findByArticleNameAndUnit_UnitName(String articleName, String unitName);
}
