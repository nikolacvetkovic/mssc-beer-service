package xyz.riocode.brewery.beer.service.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import xyz.riocode.brewery.beer.service.domain.Beer;
import xyz.riocode.brewery.common.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    BeerDto beerToBeerDtoWithInventory(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
