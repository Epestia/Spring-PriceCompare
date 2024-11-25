package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userId;

    @Column(name = "FirstName", nullable = false, length = 200)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 200)
    private String lastName;

    @Column(name = "City", nullable = false, length = 200)
    private String city;

    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Mail", nullable = false, unique = true, length = 200)
    private String mail;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    // Relation Many-to-Many avec RoleEntity
    @ManyToMany
    @JoinTable(
            name = "RolesEntity",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "RoleID")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notifications;
}