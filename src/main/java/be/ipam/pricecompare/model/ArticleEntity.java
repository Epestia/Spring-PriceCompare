package be.ipam.pricecompare.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleID;

    private String articleName;
    private String articleUnit;
    private BigDecimal articleDefaultPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    // Getters and Setters
    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleUnit() {
        return articleUnit;
    }

    public void setArticleUnit(String articleUnit) {
        this.articleUnit = articleUnit;
    }

    public BigDecimal getArticleDefaultPrice() {
        return articleDefaultPrice;
    }

    public void setArticleDefaultPrice(BigDecimal articleDefaultPrice) {
        this.articleDefaultPrice = articleDefaultPrice;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
