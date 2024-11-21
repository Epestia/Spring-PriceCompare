package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.StoreEntityDto;
import be.ipam.pricecompare.model.StoreEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoreEntityMapper {
    StoreEntity toEntity(StoreEntityDto storeEntityDto);

    StoreEntityDto toDto(StoreEntity storeEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StoreEntity partialUpdate(StoreEntityDto storeEntityDto, @MappingTarget StoreEntity storeEntity);
}