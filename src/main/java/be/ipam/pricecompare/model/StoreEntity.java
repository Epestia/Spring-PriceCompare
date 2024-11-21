package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoreID")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private Long storeId;

    @Column(name = "StoreName")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private String storeName;

    @Column(name = "StoreCity")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private String storeCity;

    @Column(name = "StoreAddress")  // Assurez-vous que le nom de la colonne est correct dans la base de données
    private String storeAddress;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceEntity> prices;
}
