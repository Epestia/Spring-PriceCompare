package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.UserEntityDto;
import be.ipam.pricecompare.service.UserService;
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

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Gestion des Utilisateurs", description = "Endpoints pour créer, lire, mettre à jour et supprimer des utilisateurs dans le système.")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Crée un nouvel utilisateur avec des rôles associés.
     *
     * @param userEntityDto les informations de l'utilisateur à créer
     * @return l'utilisateur nouvellement créé
     */
    @PostMapping
    @Operation(
            summary = "Créer un nouvel utilisateur",
            description = "Ajoute un nouvel utilisateur au système, en définissant ses informations personnelles et ses rôles."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class))),
            @ApiResponse(responseCode = "400", description = "Les données fournies sont invalides ou incomplètes.")
    })
    public ResponseEntity<UserEntityDto> createUser(@RequestBody UserEntityDto userEntityDto) {
        UserEntityDto createdUser = userService.createUser(userEntityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Supprime un utilisateur existant en fonction de son ID.
     *
     * @param id l'identifiant unique de l'utilisateur à supprimer
     * @return une réponse vide si la suppression réussit
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur existant en fonction de son identifiant unique. Cette opération est irréversible."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Utilisateur supprimé avec succès."),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé pour l'ID fourni.")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Met à jour partiellement un utilisateur en fonction des informations fournies.
     *
     * @param id            l'identifiant unique de l'utilisateur à mettre à jour
     * @param userEntityDto les informations à mettre à jour
     * @return les informations mises à jour de l'utilisateur
     */
    @PatchMapping("/{id}")
    @Operation(
            summary = "Mettre à jour partiellement un utilisateur",
            description = "Permet de modifier certains champs d'un utilisateur, comme la ville ou les rôles, sans affecter les autres données."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé pour l'ID fourni."),
            @ApiResponse(responseCode = "400", description = "Les données fournies sont invalides ou incomplètes.")
    })
    public ResponseEntity<UserEntityDto> updatePartialUser(@PathVariable Long id, @RequestBody UserEntityDto userEntityDto) {
        UserEntityDto updatedUser = userService.updatePartialUser(id, userEntityDto).orElse(null);
        return updatedUser != null
                ? ResponseEntity.ok(updatedUser)
                : ResponseEntity.notFound().build();
    }

    /**
     * Récupère les informations d'un utilisateur en fonction de son ID.
     *
     * @param id l'identifiant unique de l'utilisateur
     * @return les informations détaillées de l'utilisateur
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Récupérer un utilisateur par ID",
            description = "Permet d'obtenir les détails complets d'un utilisateur enregistré dans le système en utilisant son identifiant unique."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé et retourné avec succès.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntityDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur trouvé pour l'ID fourni.")
    })
    public ResponseEntity<UserEntityDto> findUserById(@PathVariable Long id) {
        UserEntityDto user = userService.getUserById(id).orElse(null);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }
}
