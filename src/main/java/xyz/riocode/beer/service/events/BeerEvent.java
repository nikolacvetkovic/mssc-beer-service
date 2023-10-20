package xyz.riocode.beer.service.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import xyz.riocode.beer.service.web.model.BeerDto;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -6977273018284813967L;

    private final BeerDto beerDto;

}
