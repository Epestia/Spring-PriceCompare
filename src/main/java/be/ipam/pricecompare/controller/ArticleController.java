package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des articles dans l'application.
 * Fournit des endpoints pour récupérer, créer, mettre à jour, et supprimer des articles.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * Constructeur pour initialiser le service d'articles.
     *
     * @param articleService Le service de gestion des articles.
     */
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Récupère tous les articles.
     *
     * @return Liste des articles sous forme de {@link ArticleEntityDto}.
     */
    @Operation(summary = "Récupérer tous les articles")
    @GetMapping
    public ResponseEntity<List<ArticleEntityDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    /**
     * Récupère un article par son identifiant.
     *
     * @param id L'identifiant de l'article à récupérer.
     * @return L'article avec l'identifiant donné si trouvé, sinon une réponse 404.
     */
    @Operation(summary = "Récupérer un article par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArticleEntityDto> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouvel article.
     *
     * @param articleDto Le DTO représentant l'article à créer.
     * @return L'article créé sous forme de {@link ArticleEntityDto}.
     */
    @Operation(summary = "Créer un article")
    @PostMapping
    public ResponseEntity<ArticleEntityDto> createArticle(@RequestBody ArticleEntityDto articleDto) {
        return ResponseEntity.status(201).body(articleService.createArticle(articleDto));
    }

    /**
     * Met à jour un article existant.
     *
     * @param id L'identifiant de l'article à mettre à jour.
     * @param articleDto Le DTO contenant les nouvelles données de l'article.
     * @return L'article mis à jour si l'article existe, sinon une réponse 404.
     */
    @Operation(summary = "Mettre à jour un article")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article mis à jour"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ArticleEntityDto> updateArticle(@PathVariable Long id, @RequestBody ArticleEntityDto articleDto) {
        return articleService.updateArticle(id, articleDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un article par son identifiant.
     *
     * @param id L'identifiant de l'article à supprimer.
     * @return Une réponse 204 si l'article a été supprimé avec succès, sinon une réponse 404 si l'article est introuvable.
     */
    @Operation(summary = "Supprimer un article")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Article supprimé"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Recherche un article par son nom.
     *
     * @param name Le nom de l'article à rechercher.
     * @return L'article correspondant au nom donné si trouvé, sinon une réponse 404.
     */
    @Operation(summary = "Rechercher un article par nom")
    @GetMapping("/name/{name}")
    public ResponseEntity<ArticleEntityDto> getArticleByName(@PathVariable String name) {
        return articleService.getArticleByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recherche un article par son prix.
     *
     * @param price Le prix de l'article à rechercher.
     * @return L'article correspondant au prix donné si trouvé, sinon une réponse 404.
     */
    @Operation(summary = "Rechercher un article par prix")
    @GetMapping("/price/{price}")
    public ResponseEntity<ArticleEntityDto> getArticleByPrice(@PathVariable Double price) {
        return articleService.getArticleByPrice(price)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recherche un article par son nom et son unité.
     *
     * @param name Le nom de l'article.
     * @param unit L'unité de l'article.
     * @return L'article correspondant aux critères si trouvé, sinon une réponse 404.
     */
    @Operation(summary = "Rechercher un article par nom et unité")
    @GetMapping("/name/{name}/unit/{unit}")
    public ResponseEntity<ArticleEntityDto> getArticleByNameAndUnit(@PathVariable String name, @PathVariable String unit) {
        return articleService.getArticleByNameAndUnit(name, unit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
