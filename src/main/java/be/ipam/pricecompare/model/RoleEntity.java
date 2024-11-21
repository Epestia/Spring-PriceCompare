package be.ipam.pricecompare.model;

import be.ipam.pricecompare.model.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "RoleEntity")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private Long roleId;

    @Column(name = "RoleName", nullable = false, unique = true, length = 50)
    private String roleName;

    // Relation Many-to-Many avec UserEntity
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
