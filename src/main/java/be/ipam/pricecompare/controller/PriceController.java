package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.PriceEntityDto;
import be.ipam.pricecompare.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des prix.
 * Fournit les opérations CRUD pour l'entité Price.
 */
@RestController
@RequestMapping("/api/prices")
@Tag(name = "Gestion des Prix", description = "Points d'accès pour gérer les prix")
public class PriceController {

    private final PriceService priceService;

    /**
     * Constructeur pour injecter le service PriceService.
     *
     * @param priceService le service qui gère les opérations liées aux prix
     */
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Créer un nouveau prix.
     *
     * @param priceEntityDto les détails du prix à créer
     * @return le prix créé enveloppé dans une ResponseEntity
     */
    @Operation(summary = "Créer un prix", description = "Ajoute un nouveau prix au système")
    @ApiResponse(responseCode = "201", description = "Prix créé avec succès")
    @PostMapping
    public ResponseEntity<PriceEntityDto> createPrice(@RequestBody PriceEntityDto priceEntityDto) {
        PriceEntityDto createdPrice = priceService.createPrice(priceEntityDto);
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    /**
     * Obtenir tous les prix.
     *
     * @return une liste de tous les prix
     */
    @Operation(summary = "Lister tous les prix", description = "Récupère tous les prix disponibles dans le système")
    @ApiResponse(responseCode = "200", description = "Prix récupérés avec succès")
    @GetMapping
    public ResponseEntity<List<PriceEntityDto>> getAllPrices() {
        List<PriceEntityDto> allPrices = priceService.getAllPrices();
        return new ResponseEntity<>(allPrices, HttpStatus.OK);
    }

    /**
     * Obtenir un prix par son ID.
     *
     * @param priceId l'identifiant du prix à récupérer
     * @return le prix correspondant si trouvé, ou une réponse 404 sinon
     */
    @Operation(summary = "Récupérer un prix par ID", description = "Récupère un prix spécifique en fonction de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prix récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Prix non trouvé")
    })
    @GetMapping("/{priceId}")
    public ResponseEntity<PriceEntityDto> getPriceById(@PathVariable Long priceId) {
        Optional<PriceEntityDto> price = priceService.getPriceById(priceId);
        return price.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Mettre à jour un prix existant.
     *
     * @param priceId        l'identifiant du prix à mettre à jour
     * @param priceEntityDto les nouveaux détails du prix
     * @return le prix mis à jour ou une réponse 404 si le prix n'est pas trouvé
     */
    @Operation(summary = "Mettre à jour un prix", description = "Met à jour les détails d'un prix existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prix mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Prix non trouvé")
    })
    @PutMapping("/{priceId}")
    public ResponseEntity<PriceEntityDto> updatePrice(
            @PathVariable Long priceId, @RequestBody PriceEntityDto priceEntityDto) {
        Optional<PriceEntityDto> updatedPrice = priceService.updatePrice(priceId, priceEntityDto);
        return updatedPrice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un prix par son ID.
     *
     * @param priceId l'identifiant du prix à supprimer
     * @return une réponse 204 si le prix a été supprimé ou 404 s'il n'a pas été trouvé
     */
    @Operation(summary = "Supprimer un prix", description = "Supprime un prix spécifique en fonction de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Prix supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Prix non trouvé")
    })
    @DeleteMapping("/{priceId}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long priceId) {
        boolean deleted = priceService.deletePrice(priceId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
