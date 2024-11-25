package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.NotificationEntityDto;
import be.ipam.pricecompare.mapper.ArticleEntityMapper;
import be.ipam.pricecompare.mapper.NotificationEntityMapper;
import be.ipam.pricecompare.mapper.StoreEntityMapper;
import be.ipam.pricecompare.mapper.UserEntityMapper;
import be.ipam.pricecompare.model.NotificationEntity;
import be.ipam.pricecompare.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationEntityMapper notificationEntityMapper;
    private final UserEntityMapper userEntityMapper; // Injecté dans le constructeur
    private final ArticleEntityMapper articleEntityMapper; // Si vous avez un mapper pour ArticleEntity
    private final StoreEntityMapper storeEntityMapper; // Si vous avez un mapper pour StoreEntity

    @Autowired
    public NotificationService(NotificationRepository notificationRepository,
                               NotificationEntityMapper notificationEntityMapper,
                               UserEntityMapper userEntityMapper,
                               ArticleEntityMapper articleEntityMapper,
                               StoreEntityMapper storeEntityMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationEntityMapper = notificationEntityMapper;
        this.userEntityMapper = userEntityMapper;
        this.articleEntityMapper = articleEntityMapper;
        this.storeEntityMapper = storeEntityMapper;
    }

    // Créer une nouvelle notification
    public NotificationEntityDto createNotification(NotificationEntityDto notificationEntityDto) {
        NotificationEntity notificationEntity = notificationEntityMapper.toEntity(notificationEntityDto);

        // Utilisation des mappers spécifiques pour chaque sous-objet
        notificationEntity.setUser(userEntityMapper.toEntity(notificationEntityDto.getUser()));
        notificationEntity.setArticle(articleEntityMapper.toEntity(notificationEntityDto.getArticle()));
        notificationEntity.setStore(storeEntityMapper.toEntity(notificationEntityDto.getStore()));

        notificationEntity = notificationRepository.save(notificationEntity);
        return notificationEntityMapper.toDto(notificationEntity);
    }

    // Récupérer toutes les notifications
    public List<NotificationEntityDto> getAllNotifications() {
        List<NotificationEntity> notifications = notificationRepository.findAll();
        return notificationEntityMapper.toDtoList(notifications);
    }

    // Récupérer une notification par ID
    public Optional<NotificationEntityDto> getNotificationById(Long id) {
        Optional<NotificationEntity> notificationEntity = notificationRepository.findById(id);
        return notificationEntity.map(notificationEntityMapper::toDto);
    }

    // Mettre à jour une notification (mise à jour complète)
    public Optional<NotificationEntityDto> updateNotification(Long id, NotificationEntityDto notificationEntityDto) {
        Optional<NotificationEntity> existingNotification = notificationRepository.findById(id);

        if (existingNotification.isPresent()) {
            NotificationEntity notificationEntity = existingNotification.get();
            notificationEntity.setNotificationId(id);
            notificationEntity.setUser(userEntityMapper.toEntity(notificationEntityDto.getUser())); // Utilisation correcte du mapper
            notificationEntity.setArticle(articleEntityMapper.toEntity(notificationEntityDto.getArticle()));
            notificationEntity.setStore(storeEntityMapper.toEntity(notificationEntityDto.getStore()));
            notificationEntity.setNotificationDate(notificationEntityDto.getNotificationDate());
            notificationEntity.setNotificationType(notificationEntityDto.getNotificationType());
            notificationEntity.setRead(notificationEntityDto.isRead());
            notificationEntity.setNotificationMessage(notificationEntityDto.getNotificationMessage());

            notificationEntity = notificationRepository.save(notificationEntity);
            return Optional.of(notificationEntityMapper.toDto(notificationEntity));
        }
        return Optional.empty();
    }

    // Mettre à jour une notification (mise à jour partielle)
    public Optional<NotificationEntityDto> partialUpdateNotification(Long id, NotificationEntityDto notificationEntityDto) {
        return notificationRepository.findById(id)
                .map(existingNotification -> {
                    NotificationEntity updatedNotification = notificationEntityMapper.partialUpdate(notificationEntityDto, existingNotification);
                    updatedNotification = notificationRepository.save(updatedNotification);
                    return notificationEntityMapper.toDto(updatedNotification);
                });
    }

    // Supprimer une notification par ID
    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
