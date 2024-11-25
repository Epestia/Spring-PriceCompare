package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.ArticleEntityDto;
import be.ipam.pricecompare.model.ArticleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleEntityMapper {
    ArticleEntity toEntity(ArticleEntityDto articleEntityDto);

    ArticleEntityDto toDto(ArticleEntity articleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ArticleEntity partialUpdate(ArticleEntityDto articleEntityDto, @MappingTarget ArticleEntity articleEntity);
}