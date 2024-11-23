package be.ipam.pricecompare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificationsEntity")
@Getter
@Setter
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ArticleID", nullable = true)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "StoreID", nullable = true)
    private StoreEntity store;

    @Column(name = "NotificationDate", nullable = false)
    private LocalDateTime notificationDate;

    @Column(name = "NotificationType", nullable = false, length = 50)
    private String notificationType;

    @Column(name = "IsRead", nullable = false)
    private boolean isRead;

    @Column(name = "NotificationMessage", nullable = false, length = 255)
    private String notificationMessage;
}
