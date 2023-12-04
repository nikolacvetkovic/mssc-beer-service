package xyz.riocode.brewery.beer.service.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.riocode.brewery.beer.service.services.inventory.BeerInventoryFailoverFeignClient;
import xyz.riocode.brewery.beer.service.services.inventory.BeerInventoryFeignClient;

@EnableFeignClients(clients = {BeerInventoryFeignClient.class, BeerInventoryFailoverFeignClient.class})
@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${xyz.brewery.beer-inventory-service-user}") String inventoryUser,
                                                                   @Value("${xyz.brewery.beer-inventory-service-password}") String inventoryPassword) {
        return new BasicAuthRequestInterceptor(inventoryUser, inventoryPassword);
    }

}
