package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.PriceReportEntityDto;
import be.ipam.pricecompare.model.PriceReportEntity;
import be.ipam.pricecompare.mapper.PriceReportEntityMapper;
import be.ipam.pricecompare.repository.PriceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceReportService {

    @Autowired
    private PriceReportRepository priceReportRepository;

    @Autowired
    private PriceReportEntityMapper priceReportEntityMapper;

    // Méthode pour créer un nouveau rapport de prix
    public PriceReportEntityDto createPriceReport(PriceReportEntityDto priceReportEntityDto) {
        PriceReportEntity priceReportEntity = priceReportEntityMapper.toEntity(priceReportEntityDto);
        priceReportEntity = priceReportRepository.save(priceReportEntity);
        return priceReportEntityMapper.toDto(priceReportEntity);
    }

    // Méthode pour récupérer tous les rapports de prix
    public List<PriceReportEntityDto> getAllPriceReports() {
        List<PriceReportEntity> priceReportEntities = priceReportRepository.findAll();
        return priceReportEntityMapper.toDtoList(priceReportEntities);
    }

    // Méthode pour récupérer un rapport de prix par ID
    public Optional<PriceReportEntityDto> getPriceReportById(Long id) {
        Optional<PriceReportEntity> priceReportEntity = priceReportRepository.findById(id);
        return priceReportEntity.map(priceReportEntityMapper::toDto);
    }

    // Méthode pour mettre à jour un rapport de prix
    public Optional<PriceReportEntityDto> updatePriceReport(Long id, PriceReportEntityDto priceReportEntityDto) {
        Optional<PriceReportEntity> existingReport = priceReportRepository.findById(id);

        if (existingReport.isPresent()) {
            PriceReportEntity priceReportEntity = existingReport.get();
            priceReportEntity.setReportId(id);
            priceReportEntity.setReportedPrice(priceReportEntityDto.getReportedPrice());
            priceReportEntity.setReportDate(priceReportEntityDto.getReportDate());
            priceReportEntity.setProofImage(priceReportEntityDto.getProofImage());
            priceReportEntity.setIsValidated(priceReportEntityDto.getIsValidated());

            priceReportEntity = priceReportRepository.save(priceReportEntity);
            return Optional.of(priceReportEntityMapper.toDto(priceReportEntity));
        }
        return Optional.empty();
    }

    // Méthode pour supprimer un rapport de prix par ID
    public boolean deletePriceReport(Long id) {
        if (priceReportRepository.existsById(id)) {
            priceReportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
