package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.StoreEntityDto;
import be.ipam.pricecompare.mapper.StoreEntityMapper;
import be.ipam.pricecompare.model.StoreEntity;
import be.ipam.pricecompare.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreEntityMapper storeEntityMapper;

    public StoreEntityDto addStore(StoreEntityDto storeEntityDto) {
        StoreEntity storeEntity = storeEntityMapper.toEntity(storeEntityDto);
        storeEntity = storeRepository.save(storeEntity);
        return storeEntityMapper.toDto(storeEntity);
    }

    public Optional<StoreEntityDto> getStoreById(Long storeId) {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId);
        return storeEntity.map(storeEntityMapper::toDto);
    }

    public List<StoreEntityDto> getAllStores() {
        List<StoreEntity> storeEntities = storeRepository.findAll();
        return storeEntities.stream()
                .map(storeEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StoreEntityDto> updateStore(Long storeId, StoreEntityDto storeEntityDto) {
        Optional<StoreEntity> storeEntityOptional = storeRepository.findById(storeId);

        if (storeEntityOptional.isPresent()) {
            StoreEntity storeEntity = storeEntityOptional.get();
            storeEntity.setStoreName(storeEntityDto.getStoreName());
            storeEntity.setStoreCity(storeEntityDto.getStoreCity());
            storeEntity.setStoreAddress(storeEntityDto.getStoreAddress());
            storeEntity = storeRepository.save(storeEntity);
            return Optional.of(storeEntityMapper.toDto(storeEntity));
        }
        return Optional.empty();
    }

    public boolean deleteStore(Long storeId) {
        if (storeRepository.existsById(storeId)) {
            storeRepository.deleteById(storeId);
            return true;
        }
        return false;
    }
}
