package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link be.ipam.pricecompare.model.PriceReportEntity}
 */
@Value
public class PriceReportEntityDto implements Serializable {
    Long reportId;
    ArticleEntityDto article;
    StoreEntityDto store;
    UserEntityDto user;
    Double reportedPrice;
    Date reportDate;
    String proofImage;
    Boolean isValidated;
}