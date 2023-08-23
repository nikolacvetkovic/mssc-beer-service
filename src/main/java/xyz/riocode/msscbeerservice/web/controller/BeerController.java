package xyz.riocode.msscbeerservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.riocode.msscbeerservice.services.BeerService;
import xyz.riocode.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.save(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        return ResponseEntity.noContent().build();
    }

}
