package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.mapper.ArticleEntityMapper;
import be.ipam.pricecompare.model.ArticleEntity;
import be.ipam.pricecompare.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleEntityMapper articleEntityMapper) {
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    // Récupérer tous les articles
    public List<ArticleEntityDto> getAllArticles() {
        List<ArticleEntity> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    // Récupérer un article par son ID
    public Optional<ArticleEntityDto> getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(articleEntityMapper::toDto);
    }

    // Créer un nouvel article
    public ArticleEntityDto createArticle(ArticleEntityDto articleEntityDto) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(articleEntityDto);
        ArticleEntity savedArticle = articleRepository.save(articleEntity);
        return articleEntityMapper.toDto(savedArticle);
    }

    // Mettre à jour un article


    public Optional<ArticleEntityDto> updateArticle(Long id, ArticleEntityDto articleEntityDto) {
        return articleRepository.findById(id)
                .map(existingArticle -> {
                    existingArticle.setArticleName(articleEntityDto.getArticleName());
                    existingArticle.setArticleUnit(articleEntityDto.getArticleUnit());
                    existingArticle.setArticleDefaultPrice(articleEntityDto.getArticleDefaultPrice());

                    // Conversion de LocalDateTime en Date
                    if (articleEntityDto.getLastUpdated() != null) {
                        existingArticle.setLastUpdated(Date.from(articleEntityDto.getLastUpdated().atZone(ZoneId.systemDefault()).toInstant()));
                    }

                    ArticleEntity updatedArticle = articleRepository.save(existingArticle);
                    return articleEntityMapper.toDto(updatedArticle);
                });
    }


    // Supprimer un article
    public boolean deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Trouver un article par son nom
    public Optional<ArticleEntityDto> getArticleByName(String articleName) {
        return articleRepository.findByArticleName(articleName)
                .map(articleEntityMapper::toDto);
    }

    // Trouver un article par son prix
    public Optional<ArticleEntityDto> getArticleByPrice(Double price) {
        return articleRepository.findByArticleDefaultPrice(price)
                .map(articleEntityMapper::toDto);
    }

    // Trouver un article par son nom et son unité
    public Optional<ArticleEntityDto> getArticleByNameAndUnit(String articleName, String unit) {
        return articleRepository.findByArticleNameAndArticleUnit(articleName, unit)
                .map(articleEntityMapper::toDto);
    }
}
