package be.ipam.pricecompare.dto;

import be.ipam.pricecompare.model.UnitEntity;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.ipam.pricecompare.model.ArticleEntity}
 */
@Value
public class ArticleEntityDto implements Serializable {
    Long articleId;
    String articleName;
    UnitEntity unit;
    Double articleDefaultPrice;
    LocalDateTime lastUpdated;
}

//historique