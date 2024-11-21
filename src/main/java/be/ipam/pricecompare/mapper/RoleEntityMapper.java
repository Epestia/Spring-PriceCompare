package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.RoleEntityDto;
import be.ipam.pricecompare.model.RoleEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {
    RoleEntity toEntity(RoleEntityDto roleEntityDto);

    RoleEntityDto toDto(RoleEntity roleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(RoleEntityDto roleEntityDto, @MappingTarget RoleEntity roleEntity);
}