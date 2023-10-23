package xyz.riocode.beer.service.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.riocode.beer.service.config.JmsConfig;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.beer.service.events.BrewBeerEvent;
import xyz.riocode.beer.service.events.NewInventoryEvent;
import xyz.riocode.beer.service.repositories.BeerRepository;
import xyz.riocode.beer.service.web.model.BeerDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.findById(beerDto.getId()).orElseThrow(RuntimeException::new);
        // brewing
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        log.debug(beerDto.getBeerName() + " brewed. Quantity on Hand is " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE,
                                    NewInventoryEvent.builder()
                                        .beerDto(beerDto)
                                        .build());
    }
}
