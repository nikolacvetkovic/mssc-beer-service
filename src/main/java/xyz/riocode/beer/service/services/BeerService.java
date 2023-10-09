package xyz.riocode.beer.service.services;

import org.springframework.data.domain.PageRequest;
import xyz.riocode.beer.service.web.model.BeerDto;
import xyz.riocode.beer.service.web.model.BeerPagedList;
import xyz.riocode.beer.service.web.model.BeerStyle;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto save(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);
    BeerPagedList getBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
