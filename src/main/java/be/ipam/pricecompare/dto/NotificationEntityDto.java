package be.ipam.pricecompare.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link be.ipam.pricecompare.model.NotificationEntity}
 */
@Value
public class NotificationEntityDto implements Serializable {
    Long notificationId;
    UserEntityDto user;
    ArticleEntityDto article;
    StoreEntityDto store;
    LocalDateTime notificationDate;
    String notificationType;
    boolean isRead;
    String notificationMessage;
}