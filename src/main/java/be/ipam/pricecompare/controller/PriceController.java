package be.ipam.pricecompare.controller;


import be.ipam.pricecompare.dto.PriceEntityDto;
import be.ipam.pricecompare.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Endpoint pour récupérer tous les prix
    @GetMapping
    public ResponseEntity<List<PriceEntityDto>> getAllPrices() {
        List<PriceEntityDto> prices = priceService.getAllPrices();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    // Endpoint pour récupérer un prix spécifique par ID
    @GetMapping("/{id}")
    public ResponseEntity<PriceEntityDto> getPriceById(@PathVariable("id") Long id) {
        Optional<PriceEntityDto> priceEntityDto = priceService.getPriceById(id);
        return priceEntityDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint pour créer un prix
    @PostMapping
    public ResponseEntity<PriceEntityDto> createPrice(@RequestBody PriceEntityDto priceEntityDto) {
        PriceEntityDto createdPrice = priceService.createPrice(priceEntityDto);
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un prix
    @PutMapping("/{id}")
    public ResponseEntity<PriceEntityDto> updatePrice(@PathVariable("id") Long id, @RequestBody PriceEntityDto priceEntityDto) {
        PriceEntityDto updatedPrice = priceService.updatePrice(id, priceEntityDto);
        return updatedPrice != null ? new ResponseEntity<>(updatedPrice, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint pour supprimer un prix
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable("id") Long id) {
        priceService.deletePrice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
