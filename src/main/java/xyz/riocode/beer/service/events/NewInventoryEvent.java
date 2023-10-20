package xyz.riocode.beer.service.events;

import xyz.riocode.beer.service.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
