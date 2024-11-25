package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.StoreEntityDto;
import be.ipam.pricecompare.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des magasins.
 * Fournit les opérations CRUD pour l'entité Store.
 */
@RestController
@RequestMapping("/api/stores")
@Tag(name = "Gestion des Magasins", description = "Points d'accès pour gérer les magasins")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * Ajouter un nouveau magasin.
     *
     * @param storeEntityDto les détails du magasin à ajouter
     * @return le magasin créé enveloppé dans une ResponseEntity
     */
    @Operation(summary = "Ajouter un magasin", description = "Ajoute un nouveau magasin au système")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Magasin créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes")
    })
    @PostMapping
    public ResponseEntity<StoreEntityDto> addStore(@RequestBody StoreEntityDto storeEntityDto) {
        StoreEntityDto createdStore = storeService.addStore(storeEntityDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    /**
     * Obtenir un magasin par son ID.
     *
     * @param storeId l'identifiant du magasin à récupérer
     * @return le magasin correspondant si trouvé, ou une réponse 404 sinon
     */
    @Operation(summary = "Récupérer un magasin", description = "Récupère un magasin spécifique en fonction de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Magasin trouvé et retourné"),
            @ApiResponse(responseCode = "404", description = "Magasin non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StoreEntityDto> getStoreById(@PathVariable("id") Long storeId) {
        Optional<StoreEntityDto> storeEntityDto = storeService.getStoreById(storeId);
        return storeEntityDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Obtenir tous les magasins.
     *
     * @return la liste de tous les magasins
     */
    @Operation(summary = "Lister tous les magasins", description = "Récupère tous les magasins disponibles dans le système")
    @ApiResponse(responseCode = "200", description = "Liste des magasins récupérée avec succès")
    @GetMapping
    public ResponseEntity<List<StoreEntityDto>> getAllStores() {
        List<StoreEntityDto> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    /**
     * Mettre à jour un magasin existant.
     *
     * @param storeId        l'identifiant du magasin à mettre à jour
     * @param storeEntityDto les nouveaux détails du magasin
     * @return le magasin mis à jour ou une réponse 404 si le magasin n'est pas trouvé
     */
    @Operation(summary = "Mettre à jour un magasin", description = "Met à jour les informations d'un magasin existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Magasin mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Magasin non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StoreEntityDto> updateStore(
            @PathVariable("id") Long storeId,
            @RequestBody StoreEntityDto storeEntityDto) {
        Optional<StoreEntityDto> updatedStore = storeService.updateStore(storeId, storeEntityDto);
        return updatedStore.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un magasin par son ID.
     *
     * @param storeId l'identifiant du magasin à supprimer
     * @return une réponse 204 si le magasin a été supprimé ou 404 s'il n'existe pas
     */
    @Operation(summary = "Supprimer un magasin", description = "Supprime un magasin spécifique en fonction de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Magasin supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Magasin non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long storeId) {
        if (storeService.deleteStore(storeId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
