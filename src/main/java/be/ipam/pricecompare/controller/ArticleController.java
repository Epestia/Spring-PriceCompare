package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Récupérer tous les articles", description = "Obtenez une liste de tous les articles du système")
    @GetMapping
    public ResponseEntity<List<ArticleEntityDto>> getAllArticles() {
        List<ArticleEntityDto> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @Operation(summary = "Récupérer un article par son ID", description = "Obtenez les détails de l'article par son ID unique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ArticleEntityDto> getArticleById(@PathVariable Long id) {
        Optional<ArticleEntityDto> article = articleService.getArticleById(id);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Créer un nouvel article", description = "Créer un nouvel article dans le système")
    @PostMapping
    public ResponseEntity<ArticleEntityDto> createArticle(@RequestBody ArticleEntityDto articleEntityDto) {
        ArticleEntityDto createdArticle = articleService.createArticle(articleEntityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    @Operation(summary = "Mettre à jour un article existant", description = "Mettre à jour les détails d'un article existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ArticleEntityDto> updateArticle(@PathVariable Long id, @RequestBody ArticleEntityDto articleEntityDto) {
        Optional<ArticleEntityDto> updatedArticle = articleService.updateArticle(id, articleEntityDto);
        return updatedArticle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Supprimer un article", description = "Supprimer un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean deleted = articleService.deleteArticle(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Trouver un article par son nom", description = "Obtenez les détails de l'article par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @GetMapping("/name/{articleName}")
    public ResponseEntity<ArticleEntityDto> getArticleByName(@PathVariable String articleName) {
        Optional<ArticleEntityDto> article = articleService.getArticleByName(articleName);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Trouver un article par son prix", description = "Obtenez les détails de l'article par son prix")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @GetMapping("/price/{price}")
    public ResponseEntity<ArticleEntityDto> getArticleByPrice(@PathVariable Double price) {
        Optional<ArticleEntityDto> article = articleService.getArticleByPrice(price);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Trouver un article par son nom et son unité", description = "Obtenez les détails de l'article par son nom et son unité")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Article non trouvé")
    })
    @GetMapping("/name/{articleName}/unit/{unit}")
    public ResponseEntity<ArticleEntityDto> getArticleByNameAndUnit(@PathVariable String articleName, @PathVariable String unit) {
        Optional<ArticleEntityDto> article = articleService.getArticleByNameAndUnit(articleName, unit);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
