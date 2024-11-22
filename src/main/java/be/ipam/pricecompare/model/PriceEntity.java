package be.ipam.pricecompare.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PriceID")
    private Long priceID;

    @ManyToOne
    @JoinColumn(name = "ArticleID", referencedColumnName = "ArticleID")
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "StoreID", nullable = false)
    private StoreEntity store;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "PreviousPrice")
    private BigDecimal previousPrice;

    @Column(name = "IsPromotion")
    private Boolean isPromotion;

    @Column(name = "IsEstimatedPrice")
    private Boolean isEstimatedPrice;

    // Getters and Setters
    public Long getPriceID() {
        return priceID;
    }

    public void setPriceID(Long priceID) {
        this.priceID = priceID;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(BigDecimal previousPrice) {
        this.previousPrice = previousPrice;
    }

    public Boolean getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(Boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public Boolean getIsEstimatedPrice() {
        return isEstimatedPrice;
    }

    public void setIsEstimatedPrice(Boolean isEstimatedPrice) {
        this.isEstimatedPrice = isEstimatedPrice;
    }
}
