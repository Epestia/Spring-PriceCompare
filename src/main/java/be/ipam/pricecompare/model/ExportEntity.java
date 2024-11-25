package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exportsEntity")
public class ExportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExportID")
    private Long exportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private UserEntity user;  // Relation avec UserEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArticleID")
    private ArticleEntity article;  // Relation avec ArticleEntity

    @Column(name = "ExportDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exportDate;

    @Column(name = "ExportType")
    private String exportType;

    @Column(name = "ExportData")
    private String exportData;

    // Getters et Setters

    public Long getExportId() {
        return exportId;
    }

    public void setExportId(Long exportId) {
        this.exportId = exportId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getExportData() {
        return exportData;
    }

    public void setExportData(String exportData) {
        this.exportData = exportData;
    }
}
