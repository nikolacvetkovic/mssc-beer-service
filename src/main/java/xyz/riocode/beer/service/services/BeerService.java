package xyz.riocode.beer.service.services;

import org.springframework.data.domain.PageRequest;
import xyz.riocode.common.model.BeerDto;
import xyz.riocode.common.model.BeerPagedList;
import xyz.riocode.common.model.BeerStyle;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto getByUpc(String beerUpc);
    BeerDto save(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);
    BeerPagedList getBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
