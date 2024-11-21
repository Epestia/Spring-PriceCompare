package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.StoreEntityDto;
import be.ipam.pricecompare.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Ajouter un magasin
    @PostMapping
    public ResponseEntity<StoreEntityDto> addStore(@RequestBody StoreEntityDto storeEntityDto) {
        StoreEntityDto createdStore = storeService.addStore(storeEntityDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // Obtenir un magasin par son ID
    @GetMapping("/{id}")
    public ResponseEntity<StoreEntityDto> getStoreById(@PathVariable("id") Long storeId) {
        Optional<StoreEntityDto> storeEntityDto = storeService.getStoreById(storeId);
        return storeEntityDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtenir tous les magasins
    @GetMapping
    public ResponseEntity<List<StoreEntityDto>> getAllStores() {
        List<StoreEntityDto> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Mettre à jour un magasin
    @PutMapping("/{id}")
    public ResponseEntity<StoreEntityDto> updateStore(
            @PathVariable("id") Long storeId,
            @RequestBody StoreEntityDto storeEntityDto) {
        Optional<StoreEntityDto> updatedStore = storeService.updateStore(storeId, storeEntityDto);
        return updatedStore.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un magasin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long storeId) {
        if (storeService.deleteStore(storeId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
