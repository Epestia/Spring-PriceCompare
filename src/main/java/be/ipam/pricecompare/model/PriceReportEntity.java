package be.ipam.pricecompare.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "priceReportsEntity")
public class PriceReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportID")
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArticleID", nullable = false)
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreID", nullable = false)
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private UserEntity user;

    @Column(name = "ReportedPrice", nullable = false)
    private Double reportedPrice;

    @Column(name = "ReportDate", nullable = false)
    private Date reportDate;

    @Column(name = "ProofImage")
    private String proofImage;

    @Column(name = "IsValidated", nullable = false)
    private Boolean isValidated;

}
