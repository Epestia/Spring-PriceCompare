package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.PriceEntityDto;
import be.ipam.pricecompare.model.PriceEntity;
import be.ipam.pricecompare.mapper.PriceEntityMapper;
import be.ipam.pricecompare.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    public PriceService(PriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        this.priceRepository = priceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    public PriceEntityDto createPrice(PriceEntityDto priceEntityDto) {
        PriceEntity priceEntity = priceEntityMapper.toEntity(priceEntityDto);
        PriceEntity savedPrice = priceRepository.save(priceEntity);
        return priceEntityMapper.toDto(savedPrice);
    }

    public List<PriceEntityDto> getAllPrices() {
        return priceRepository.findAll()  // Correction ici
                .stream()
                .map(priceEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PriceEntityDto> getPriceById(Long id) {
        return priceRepository.findById(id)
                .map(priceEntityMapper::toDto);
    }

    public Optional<PriceEntityDto> updatePrice(Long id, PriceEntityDto priceEntityDto) {
        return priceRepository.findById(id)
                .map(existingPrice -> {
                    PriceEntity updatedEntity = priceEntityMapper.toEntity(priceEntityDto);
                    updatedEntity.setPriceID(existingPrice.getPriceID());  // Maintenir l'ID
                    return priceEntityMapper.toDto(priceRepository.save(updatedEntity));
                });
    }

    public boolean deletePrice(Long id) {
        if (priceRepository.existsById(id)) {
            priceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
