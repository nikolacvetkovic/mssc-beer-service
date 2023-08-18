package xyz.riocode.msscbeerservice.web.mapper;

import org.mapstruct.Mapper;
import xyz.riocode.msscbeerservice.domain.Beer;
import xyz.riocode.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
