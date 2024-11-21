package be.ipam.pricecompare.repository;


import be.ipam.pricecompare.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
