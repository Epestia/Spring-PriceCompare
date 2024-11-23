package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.PriceReportEntityDto;
import be.ipam.pricecompare.service.PriceReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price-reports")
@Tag(name = "Gestion des Rapports de Prix", description = "Endpoints pour créer, lire, mettre à jour et supprimer les rapports de prix.")
public class PriceReportController {

    @Autowired
    private PriceReportService priceReportEntityService;

    /**
     * Crée un nouveau rapport de prix.
     *
     * @param priceReportEntityDto les informations du rapport de prix à créer
     * @return le rapport de prix nouvellement créé
     */
    @PostMapping
    @Operation(
            summary = "Créer un nouveau rapport de prix",
            description = "Ajoute un nouveau rapport de prix au système, incluant les informations de l'article, du magasin et de l'utilisateur."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rapport de prix créé avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceReportEntityDto.class))),
            @ApiResponse(responseCode = "400", description = "Les données fournies sont invalides ou incomplètes.")
    })
    public ResponseEntity<PriceReportEntityDto> createPriceReport(@RequestBody PriceReportEntityDto priceReportEntityDto) {
        PriceReportEntityDto createdPriceReport = priceReportEntityService.createPriceReport(priceReportEntityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPriceReport);
    }

    /**
     * Récupère tous les rapports de prix.
     *
     * @return une liste de tous les rapports de prix
     */
    @GetMapping
    @Operation(
            summary = "Récupérer tous les rapports de prix",
            description = "Récupère tous les rapports de prix dans le système."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des rapports de prix retournée avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceReportEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun rapport de prix trouvé.")
    })
    public ResponseEntity<List<PriceReportEntityDto>> findAllPriceReports() {
        List<PriceReportEntityDto> priceReports = priceReportEntityService.getAllPriceReports();
        return priceReports.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(priceReports);
    }

    /**
     * Récupère un rapport de prix en fonction de son ID.
     *
     * @param id l'identifiant unique du rapport de prix
     * @return le rapport de prix trouvé
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer un rapport de prix par ID",
            description = "Permet d'obtenir les détails complets d'un rapport de prix enregistré dans le système en utilisant son identifiant unique."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rapport de prix trouvé et retourné avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceReportEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun rapport de prix trouvé pour l'ID fourni.")
    })
    public ResponseEntity<PriceReportEntityDto> findPriceReportById(@PathVariable Long id) {
        PriceReportEntityDto priceReport = priceReportEntityService.getPriceReportById(id).orElse(null);
        return priceReport != null
                ? ResponseEntity.ok(priceReport)
                : ResponseEntity.notFound().build();
    }

    /**
     * Met à jour un rapport de prix existant.
     *
     * @param id                     l'identifiant unique du rapport de prix à mettre à jour
     * @param priceReportEntityDto   les informations mises à jour
     * @return le rapport de prix mis à jour
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Mettre à jour un rapport de prix",
            description = "Met à jour un rapport de prix existant avec les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rapport de prix mis à jour avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceReportEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Rapport de prix non trouvé pour l'ID fourni."),
            @ApiResponse(responseCode = "400", description = "Les données fournies sont invalides ou incomplètes.")
    })
    public ResponseEntity<PriceReportEntityDto> updatePriceReport(@PathVariable Long id, @RequestBody PriceReportEntityDto priceReportEntityDto) {
        PriceReportEntityDto updatedPriceReport = priceReportEntityService.updatePriceReport(id, priceReportEntityDto).orElse(null);
        return updatedPriceReport != null
                ? ResponseEntity.ok(updatedPriceReport)
                : ResponseEntity.notFound().build();
    }

    /**
     * Supprime un rapport de prix en fonction de son ID.
     *
     * @param id l'identifiant unique du rapport de prix à supprimer
     * @return une réponse vide si la suppression réussit
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un rapport de prix",
            description = "Supprime un rapport de prix existant en fonction de son identifiant unique. Cette opération est irréversible."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rapport de prix supprimé avec succès."),
            @ApiResponse(responseCode = "404", description = "Rapport de prix non trouvé pour l'ID fourni.")
    })
    public ResponseEntity<Void> deletePriceReport(@PathVariable Long id) {
        boolean isDeleted = priceReportEntityService.deletePriceReport(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
