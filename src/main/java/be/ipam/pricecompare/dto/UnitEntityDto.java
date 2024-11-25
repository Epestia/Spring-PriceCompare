package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.ipam.pricecompare.model.UnitEntity}
 */
@Value
public class UnitEntityDto implements Serializable {
    Long unitId;
    String unitName;
}