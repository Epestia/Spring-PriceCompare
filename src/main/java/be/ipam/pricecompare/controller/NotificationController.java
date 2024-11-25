package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.NotificationEntityDto;
import be.ipam.pricecompare.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur pour gérer les opérations liées aux notifications.
 * Fournit des points d'accès pour créer, lire, mettre à jour et supprimer des notifications.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Crée une nouvelle notification.
     *
     * @param dto les données de la notification à créer
     * @return la notification créée
     */
    @Operation(summary = "Créer une nouvelle notification", description = "Crée une notification et la retourne.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification créée avec succès",
                    content = @Content(schema = @Schema(implementation = NotificationEntityDto.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides fournies", content = @Content)
    })
    @PostMapping
    public ResponseEntity<NotificationEntityDto> createNotification(@RequestBody NotificationEntityDto dto) {
        NotificationEntityDto createdNotification = notificationService.createNotification(dto);
        return ResponseEntity.ok(createdNotification);
    }

    /**
     * Récupère une notification par son ID.
     *
     * @param id l'identifiant de la notification à récupérer
     * @return la notification correspondante ou une erreur 404 si elle n'existe pas
     */
    @Operation(summary = "Récupérer une notification par ID", description = "Récupère une notification à partir de son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification trouvée",
                    content = @Content(schema = @Schema(implementation = NotificationEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Notification introuvable", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntityDto> getNotificationById(@PathVariable Long id) {
        Optional<NotificationEntityDto> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère toutes les notifications.
     *
     * @return une liste de toutes les notifications
     */
    @Operation(summary = "Récupérer toutes les notifications", description = "Récupère une liste de toutes les notifications.")
    @ApiResponse(responseCode = "200", description = "Liste des notifications retournée",
            content = @Content(schema = @Schema(implementation = List.class)))
    @GetMapping
    public ResponseEntity<List<NotificationEntityDto>> getAllNotifications() {
        List<NotificationEntityDto> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    /**
     * Met à jour une notification par son ID.
     *
     * @param id  l'identifiant de la notification à mettre à jour
     * @param dto les nouvelles données de la notification
     * @return la notification mise à jour ou une erreur 404 si elle n'existe pas
     */
    @Operation(summary = "Mettre à jour une notification", description = "Met à jour les données d'une notification existante.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification mise à jour avec succès",
                    content = @Content(schema = @Schema(implementation = NotificationEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Notification introuvable", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<NotificationEntityDto> updateNotification(@PathVariable Long id, @RequestBody NotificationEntityDto dto) {
        Optional<NotificationEntityDto> updatedNotification = notificationService.updateNotification(id, dto);
        return updatedNotification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une notification par son ID.
     *
     * @param id l'identifiant de la notification à supprimer
     * @return une réponse vide si la suppression est réussie
     */
    @Operation(summary = "Supprimer une notification", description = "Supprime une notification en fonction de son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Notification supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Notification introuvable", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
