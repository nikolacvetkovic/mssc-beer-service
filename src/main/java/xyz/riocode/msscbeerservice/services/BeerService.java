package xyz.riocode.msscbeerservice.services;

import xyz.riocode.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId);
    BeerDto save(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);

}
