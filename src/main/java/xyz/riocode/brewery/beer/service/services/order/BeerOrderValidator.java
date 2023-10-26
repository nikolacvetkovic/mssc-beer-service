package xyz.riocode.brewery.beer.service.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.riocode.brewery.beer.service.repositories.BeerRepository;
import xyz.riocode.brewery.common.model.BeerOrderDto;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger beersNotFound = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            if (beerRepository.findByUpc(orderLine.getUpc()).isEmpty()) {
                beersNotFound.incrementAndGet();
            }
        });
        return beersNotFound.get() == 0;
    }

}
