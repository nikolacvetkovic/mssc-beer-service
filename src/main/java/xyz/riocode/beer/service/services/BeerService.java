package xyz.riocode.beer.service.services;

import xyz.riocode.beer.service.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId);
    BeerDto save(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);

}
