package xyz.riocode.brewery.beer.service.services.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.riocode.brewery.beer.service.services.inventory.model.BeerInventoryDto;

import java.util.List;

@FeignClient(name = "beer-inventory-failover")
public interface BeerInventoryFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/beer/inventory/failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
