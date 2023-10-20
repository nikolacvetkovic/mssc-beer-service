package xyz.riocode.beer.service.events;

import xyz.riocode.beer.service.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
