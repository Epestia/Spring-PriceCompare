package be.ipam.pricecompare.service;


import be.ipam.pricecompare.dto.PriceEntityDto;
import be.ipam.pricecompare.mapper.PriceEntityMapper;
import be.ipam.pricecompare.model.PriceEntity;
import be.ipam.pricecompare.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceMapper;

    @Autowired
    public PriceService(PriceRepository priceRepository, PriceEntityMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    public List<PriceEntityDto> getAllPrices() {
        List<PriceEntity> prices = priceRepository.findAll();
        return priceMapper.toDtoList(prices);
    }

    public Optional<PriceEntityDto> getPriceById(Long priceID) {
        Optional<PriceEntity> priceEntity = priceRepository.findById(priceID);
        return priceEntity.map(priceMapper::toDto);
    }

    public PriceEntityDto createPrice(PriceEntityDto priceEntityDto) {
        PriceEntity priceEntity = priceMapper.toEntity(priceEntityDto);
        priceEntity = priceRepository.save(priceEntity);
        return priceMapper.toDto(priceEntity);
    }

    public PriceEntityDto updatePrice(Long priceID, PriceEntityDto priceEntityDto) {
        Optional<PriceEntity> existingPriceEntity = priceRepository.findById(priceID);
        if (existingPriceEntity.isPresent()) {
            PriceEntity priceEntity = priceMapper.toEntity(priceEntityDto);
            priceEntity.setPriceID(priceID);  // Set the existing ID
            priceEntity = priceRepository.save(priceEntity);
            return priceMapper.toDto(priceEntity);
        }
        return null;
    }

    public void deletePrice(Long priceID) {
        priceRepository.deleteById(priceID);
    }
}
