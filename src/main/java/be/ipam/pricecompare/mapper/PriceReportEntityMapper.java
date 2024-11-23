package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.PriceReportEntityDto;
import be.ipam.pricecompare.model.PriceReportEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ArticleEntityMapper.class, StoreEntityMapper.class, UserEntityMapper.class})
public interface PriceReportEntityMapper {
    PriceReportEntity toEntity(PriceReportEntityDto priceReportEntityDto);

    PriceReportEntityDto toDto(PriceReportEntity priceReportEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceReportEntity partialUpdate(PriceReportEntityDto priceReportEntityDto, @MappingTarget PriceReportEntity priceReportEntity);

    List<PriceReportEntityDto> toDtoList(List<PriceReportEntity> priceReportEntities);
}