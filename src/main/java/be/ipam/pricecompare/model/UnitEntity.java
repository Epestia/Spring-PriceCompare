package be.ipam.pricecompare.model;


import jakarta.persistence.*;

@Entity
@Table(name = "UnitEntity")
public class UnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UnitID")
    private Long unitId;

    @Column(name = "UnitName", nullable = false, length = 50, unique = true)
    private String unitName;

    // Getters et Setters
    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
