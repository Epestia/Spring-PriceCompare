package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.model.ArticleEntity;
import be.ipam.pricecompare.mapper.ArticleEntityMapper;
import be.ipam.pricecompare.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;  // Injecter le mapper

    // Constructeur avec injection de dépendance
    public ArticleService(ArticleRepository articleRepository, ArticleEntityMapper articleEntityMapper) {
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    public List<ArticleEntityDto> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(articleEntityMapper::toDto) // Utilisation de l'instance injectée
                .collect(Collectors.toList());
    }

    public Optional<ArticleEntityDto> getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(articleEntityMapper::toDto); // Utilisation de l'instance injectée
    }

    public ArticleEntityDto createArticle(ArticleEntityDto articleDto) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(articleDto); // Utilisation de l'instance injectée
        ArticleEntity savedArticle = articleRepository.save(articleEntity);
        return articleEntityMapper.toDto(savedArticle); // Utilisation de l'instance injectée
    }

    public Optional<ArticleEntityDto> updateArticle(Long id, ArticleEntityDto articleDto) {
        return articleRepository.findById(id).map(existingArticle -> {
            ArticleEntity updatedEntity = articleEntityMapper.toEntity(articleDto); // Utilisation de l'instance injectée
            updatedEntity.setArticleId(existingArticle.getArticleId()); // Maintenir l'ID
            return articleEntityMapper.toDto(articleRepository.save(updatedEntity)); // Utilisation de l'instance injectée
        });
    }

    public boolean deleteArticle(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ArticleEntityDto> getArticleByName(String name) {
        return articleRepository.findByArticleName(name)
                .map(articleEntityMapper::toDto); // Utilisation de l'instance injectée
    }

    public Optional<ArticleEntityDto> getArticleByPrice(Double price) {
        return articleRepository.findByArticleDefaultPrice(price)
                .map(articleEntityMapper::toDto); // Utilisation de l'instance injectée
    }

    public Optional<ArticleEntityDto> getArticleByNameAndUnit(String name, String unit) {
        return articleRepository.findByArticleNameAndUnit_UnitName(name, unit)
                .map(articleEntityMapper::toDto); // Utilisation de l'instance injectée
    }
}
