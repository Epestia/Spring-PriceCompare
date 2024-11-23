package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.PriceEntityDto;
import be.ipam.pricecompare.model.PriceEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ArticleEntityMapper.class})
public interface PriceEntityMapper {

    PriceEntity toEntity(PriceEntityDto priceEntityDto);

    PriceEntityDto toDto(PriceEntity priceEntity);

    List<PriceEntityDto> toDtoList(List<PriceEntity> priceEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PriceEntity partialUpdate(PriceEntityDto priceEntityDto, @MappingTarget PriceEntity priceEntity);
}
