package xyz.riocode.beer.service.web.mapper;

import org.mapstruct.Mapper;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.beer.service.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
