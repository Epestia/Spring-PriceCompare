package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.ExportEntityDto;
import be.ipam.pricecompare.model.ExportEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserEntityMapper.class, ArticleEntityMapper.class})
public interface ExportEntityMapper {
    ExportEntity toEntity(ExportEntityDto exportEntityDto);

    ExportEntityDto toDto(ExportEntity exportEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExportEntity partialUpdate(ExportEntityDto exportEntityDto, @MappingTarget ExportEntity exportEntity);
}