package be.ipam.pricecompare.controller;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Récupérer tous les articles")
    @GetMapping
    public ResponseEntity<List<ArticleEntityDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

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

    @Operation(summary = "Créer un article")
    @PostMapping
    public ResponseEntity<ArticleEntityDto> createArticle(@RequestBody ArticleEntityDto articleDto) {
        return ResponseEntity.status(201).body(articleService.createArticle(articleDto));
    }

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

    @Operation(summary = "Rechercher un article par nom")
    @GetMapping("/name/{name}")
    public ResponseEntity<ArticleEntityDto> getArticleByName(@PathVariable String name) {
        return articleService.getArticleByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rechercher un article par prix")
    @GetMapping("/price/{price}")
    public ResponseEntity<ArticleEntityDto> getArticleByPrice(@PathVariable Double price) {
        return articleService.getArticleByPrice(price)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Rechercher un article par nom et unité")
    @GetMapping("/name/{name}/unit/{unit}")
    public ResponseEntity<ArticleEntityDto> getArticleByNameAndUnit(@PathVariable String name, @PathVariable String unit) {
        return articleService.getArticleByNameAndUnit(name, unit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
