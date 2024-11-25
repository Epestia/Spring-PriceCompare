package be.ipam.pricecompare.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.pricecompare.model.StoreEntity}
 */
@Getter
@Setter
@Value
public class StoreEntityDto implements Serializable {
    Long storeId;
    String storeName;
    String storeCity;
    String storeAddress;
}
