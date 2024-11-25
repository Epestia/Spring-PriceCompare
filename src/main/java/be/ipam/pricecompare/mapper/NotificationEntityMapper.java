package be.ipam.pricecompare.mapper;

import be.ipam.pricecompare.dto.NotificationEntityDto;
import be.ipam.pricecompare.model.NotificationEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserEntityMapper.class, ArticleEntityMapper.class, StoreEntityMapper.class})
public interface NotificationEntityMapper {
    NotificationEntity toEntity(NotificationEntityDto notificationEntityDto);

    NotificationEntityDto toDto(NotificationEntity notificationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationEntity partialUpdate(NotificationEntityDto notificationEntityDto, @MappingTarget NotificationEntity notificationEntity);

    List<NotificationEntityDto> toDtoList(List<NotificationEntity> entities);
}