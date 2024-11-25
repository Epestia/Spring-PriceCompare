package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.ExportEntityDto;
import be.ipam.pricecompare.service.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations liées aux exportations de données.
 * Ce contrôleur permet de créer, lire, mettre à jour, supprimer et récupérer des exportations dans le système.
 */
@RestController
@RequestMapping("/exports")
public class ExportController {

    private final ExportService exportService;

    /**
     * Constructeur pour initialiser le service des exportations.
     *
     * @param exportService Le service utilisé pour gérer les exportations de données.
     */
    @Autowired
    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    /**
     * Crée un nouvel export.
     *
     * @param exportEntityDto Les données de l'export à créer.
     * @return L'export créé avec un code HTTP 201 (CREATED).
     */
    @Operation(summary = "Créer un nouvel export")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Export créé")
    })
    @PostMapping
    public ResponseEntity<ExportEntityDto> createExport(@RequestBody ExportEntityDto exportEntityDto) {
        ExportEntityDto createdExport = exportService.createExport(exportEntityDto);
        return new ResponseEntity<>(createdExport, HttpStatus.CREATED);
    }

    /**
     * Récupère la liste de toutes les exportations.
     *
     * @return Liste des exportations avec un code HTTP 200 (OK).
     */
    @Operation(summary = "Récupérer tous les exports")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des exports récupérée")
    })
    @GetMapping
    public ResponseEntity<List<ExportEntityDto>> getAllExports() {
        List<ExportEntityDto> exports = exportService.getAllExports();
        return new ResponseEntity<>(exports, HttpStatus.OK);
    }

    /**
     * Récupère une exportation par son ID.
     *
     * @param exportId L'ID de l'export à récupérer.
     * @return L'export trouvé avec un code HTTP 200 (OK), ou un code HTTP 404 (NOT_FOUND) si l'export n'existe pas.
     */
    @Operation(summary = "Récupérer un export par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Export trouvé"),
            @ApiResponse(responseCode = "404", description = "Export non trouvé")
    })
    @GetMapping("/{exportId}")
    public ResponseEntity<ExportEntityDto> getExportById(@PathVariable Long exportId) {
        ExportEntityDto export = exportService.getExportById(exportId);
        if (export == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(export, HttpStatus.OK);
    }

    /**
     * Met à jour une exportation existante.
     *
     * @param exportId        L'ID de l'export à mettre à jour.
     * @param exportEntityDto Les nouvelles données de l'export.
     * @return L'export mis à jour avec un code HTTP 200 (OK), ou un code HTTP 404 (NOT_FOUND) si l'export n'existe pas.
     */
    @Operation(summary = "Mettre à jour un export")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Export mis à jour"),
            @ApiResponse(responseCode = "404", description = "Export non trouvé")
    })
    @PutMapping("/{exportId}")
    public ResponseEntity<ExportEntityDto> updateExport(@PathVariable Long exportId, @RequestBody ExportEntityDto exportEntityDto) {
        ExportEntityDto updatedExport = exportService.updateExport(exportId, exportEntityDto);
        if (updatedExport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedExport, HttpStatus.OK);
    }

    /**
     * Supprime une exportation existante par son ID.
     *
     * @param exportId L'ID de l'export à supprimer.
     * @return Un code HTTP 204 (NO_CONTENT) si l'export est supprimé avec succès,
     *         ou un code HTTP 404 (NOT_FOUND) si l'export n'existe pas.
     */
    @Operation(summary = "Supprimer un export")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Export supprimé"),
            @ApiResponse(responseCode = "404", description = "Export non trouvé")
    })
    @DeleteMapping("/{exportId}")
    public ResponseEntity<Void> deleteExport(@PathVariable Long exportId) {
        boolean isDeleted = exportService.deleteExport(exportId);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
