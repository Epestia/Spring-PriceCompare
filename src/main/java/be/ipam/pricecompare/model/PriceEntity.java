package be.ipam.pricecompare.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private Long priceID;

    @ManyToOne
    @JoinColumn(name = "ArticleID", referencedColumnName = "ArticleID")
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "StoreID", referencedColumnName = "StoreID")
    private StoreEntity store;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private Date endDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "previous_price")
    private BigDecimal previousPrice;

    @Column(name = "is_promotion")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private Boolean isPromotion;

    @Column(name = "is_estimated_price")  // Assurez-vous que le nom de la colonne est correct dans la base de données
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
