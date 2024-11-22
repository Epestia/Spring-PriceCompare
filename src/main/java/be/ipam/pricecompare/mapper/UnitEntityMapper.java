package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.UnitEntityDto;
import be.ipam.pricecompare.model.UnitEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnitEntityMapper {
    UnitEntity toEntity(UnitEntityDto unitEntityDto);

    UnitEntityDto toDto(UnitEntity unitEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UnitEntity partialUpdate(UnitEntityDto unitEntityDto, @MappingTarget UnitEntity unitEntity);
}