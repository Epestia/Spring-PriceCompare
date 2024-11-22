package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ArticleEntity")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArticleID")
    private Long articleId;

    @Column(name = "ArticleName", nullable = false, length = 255)
    private String articleName;

    @ManyToOne
    @JoinColumn(name = "UnitID", nullable = false)
    private UnitEntity unit;

    @Column(name = "ArticleDefaultPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal articleDefaultPrice;

    @Column(name = "LastUpdated", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    // Getters et Setters
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public BigDecimal getArticleDefaultPrice() {
        return articleDefaultPrice;
    }

    public void setArticleDefaultPrice(BigDecimal articleDefaultPrice) {
        this.articleDefaultPrice = articleDefaultPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
