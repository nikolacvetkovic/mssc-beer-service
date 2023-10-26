package xyz.riocode.brewery.beer.service.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.riocode.brewery.beer.service.services.BeerService;
import xyz.riocode.brewery.common.model.BeerDto;
import xyz.riocode.brewery.common.model.BeerPagedList;
import xyz.riocode.brewery.common.model.BeerStyle;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(required = false, defaultValue = "25") Integer pageSize,
                                                   @RequestParam(required = false) String beerName,
                                                   @RequestParam(required = false) BeerStyle beerStyle,
                                                   @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand) {

        BeerPagedList beerList = beerService.getBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("/upc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc) {
        return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
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
