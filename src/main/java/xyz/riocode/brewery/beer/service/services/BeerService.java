package xyz.riocode.brewery.beer.service.services;

import org.springframework.data.domain.PageRequest;
import xyz.riocode.brewery.common.model.BeerDto;
import xyz.riocode.brewery.common.model.BeerPagedList;
import xyz.riocode.brewery.common.model.BeerStyle;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto getByUpc(String beerUpc);
    BeerDto save(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);
    BeerPagedList getBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
