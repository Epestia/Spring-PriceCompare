package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notifications;
}
