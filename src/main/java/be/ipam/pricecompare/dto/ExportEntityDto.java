package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link be.ipam.pricecompare.model.ExportEntity}
 */
@Value
public class ExportEntityDto implements Serializable {
    Long exportId;
    UserEntityDto user;
    ArticleEntityDto article;
    Date exportDate;
    String exportType;
    String exportData;
}

