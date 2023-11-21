package xyz.riocode.brewery.beer.service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import xyz.riocode.brewery.beer.service.services.inventory.BeerInventoryFailoverFeignClient;
import xyz.riocode.brewery.beer.service.services.inventory.BeerInventoryFeignClient;

@EnableFeignClients(clients = {BeerInventoryFeignClient.class, BeerInventoryFailoverFeignClient.class})
@Configuration
public class FeignClientConfig {}
