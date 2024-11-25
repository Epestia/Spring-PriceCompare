package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.UserEntityDto;
import be.ipam.pricecompare.model.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {
    UserEntity toEntity(UserEntityDto userEntityDto);

    UserEntityDto toDto(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserEntityDto userEntityDto, @MappingTarget UserEntity userEntity);
}