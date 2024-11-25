package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.ExportEntityDto;
import be.ipam.pricecompare.mapper.ExportEntityMapper;
import be.ipam.pricecompare.model.ExportEntity;
import be.ipam.pricecompare.repository.ExportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportService {

    private final ExportRepository exportRepository;
    private final ExportEntityMapper exportEntityMapper;

    @Autowired
    public ExportService(ExportRepository exportRepository, ExportEntityMapper exportEntityMapper) {
        this.exportRepository = exportRepository;
        this.exportEntityMapper = exportEntityMapper;
    }

    public ExportEntityDto createExport(ExportEntityDto exportEntityDto) {
        ExportEntity exportEntity = exportEntityMapper.toEntity(exportEntityDto);
        ExportEntity savedExportEntity = exportRepository.save(exportEntity);
        return exportEntityMapper.toDto(savedExportEntity);
    }

    public List<ExportEntityDto> getAllExports() {
        List<ExportEntity> exportEntities = exportRepository.findAll();
        return exportEntities.stream()
                .map(exportEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    public ExportEntityDto getExportById(Long exportId) {
        return exportRepository.findById(exportId)
                .map(exportEntityMapper::toDto)
                .orElse(null);
    }

    public ExportEntityDto updateExport(Long exportId, ExportEntityDto exportEntityDto) {
        if (!exportRepository.existsById(exportId)) {
            return null;
        }
        ExportEntity exportEntity = exportEntityMapper.toEntity(exportEntityDto);
        exportEntity.setExportId(exportId);
        ExportEntity updatedExportEntity = exportRepository.save(exportEntity);
        return exportEntityMapper.toDto(updatedExportEntity);
    }

    public boolean deleteExport(Long exportId) {
        if (exportRepository.existsById(exportId)) {
            exportRepository.deleteById(exportId);
            return true;
        }
        return false;
    }
}
