package xyz.riocode.beer.service.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.beer.service.services.inventory.BeerInventoryService;
import xyz.riocode.common.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {

    @Autowired
    private BeerMapper beerMapper;
    @Autowired
    private BeerInventoryService beerInventoryService;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return beerMapper.beerToBeerDto(beer);
    }
    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }
    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }


}
