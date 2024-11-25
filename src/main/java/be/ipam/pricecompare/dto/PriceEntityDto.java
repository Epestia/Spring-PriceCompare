package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for {@link be.ipam.pricecompare.model.PriceEntity}
 */
@Value
public class PriceEntityDto implements Serializable {
    Long priceID;
    ArticleEntityDto article;
    StoreEntityDto store;
    Date startDate;
    Date endDate;
    BigDecimal price;
    BigDecimal previousPrice;
    Boolean isPromotion;
    Boolean isEstimatedPrice;
}