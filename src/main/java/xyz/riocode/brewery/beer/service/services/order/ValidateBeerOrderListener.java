package xyz.riocode.brewery.beer.service.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import xyz.riocode.brewery.beer.service.config.JmsConfig;
import xyz.riocode.brewery.common.events.ValidateBeerOrderEvent;
import xyz.riocode.brewery.common.events.ValidateBeerOrderResultEvent;

@RequiredArgsConstructor
@Service
public class ValidateBeerOrderListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @JmsListener(destination = JmsConfig.VALIDATE_BEER_ORDER_REQ_QUEUE)
    public void listen(ValidateBeerOrderEvent event) {
        ValidateBeerOrderResultEvent validateBeerOrderResultEvent = ValidateBeerOrderResultEvent.builder()
                .orderId(event.getBeerOrderDto().getId())
                .isValid(beerOrderValidator.validateOrder(event.getBeerOrderDto()))
                .build();
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_BEER_ORDER_RES_QUEUE, validateBeerOrderResultEvent);
    }

}
