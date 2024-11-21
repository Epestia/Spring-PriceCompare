package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.ipam.pricecompare.model.ArticleEntity}
 */
@Value
public class ArticleEntityDto implements Serializable {
    Long articleId;
    String articleName;
    String articleUnit;
    BigDecimal articleDefaultPrice;
    LocalDateTime lastUpdated;
}