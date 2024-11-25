package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link be.ipam.pricecompare.model.UserEntity}
 */
@Value
public class UserEntityDto implements Serializable {
    Long userId;
    String firstName;
    String lastName;
    String city;
    LocalDate birthDate;
    String mail;
    String passwordHash;
    Set<RoleEntityDto> roles;
}
