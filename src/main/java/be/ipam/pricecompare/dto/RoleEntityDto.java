package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link be.ipam.pricecompare.model.RoleEntity}
 */
@Value
public class RoleEntityDto implements Serializable {
    Long roleId;
    String roleName;
}