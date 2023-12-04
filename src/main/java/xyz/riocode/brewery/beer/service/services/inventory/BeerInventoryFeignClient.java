package xyz.riocode.brewery.beer.service.services.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.riocode.brewery.beer.service.config.FeignClientConfig;
import xyz.riocode.brewery.beer.service.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "beer-inventory-service", fallback = BeerInventoryFailoverFeignClientImpl.class,
            configuration = FeignClientConfig.class)
public interface BeerInventoryFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/beer/{beerId}/inventory")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
