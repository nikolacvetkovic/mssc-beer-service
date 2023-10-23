package xyz.riocode.beer.service.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.riocode.beer.service.config.JmsConfig;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.beer.service.events.BrewBeerEvent;
import xyz.riocode.beer.service.repositories.BeerRepository;
import xyz.riocode.beer.service.services.inventory.BeerInventoryService;
import xyz.riocode.beer.service.web.mapper.BeerMapper;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug(beer.getBeerName() + " min OnHand is: " + beer.getMinOnHand());
            log.debug(beer.getBeerName() + "current inventory is: " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
