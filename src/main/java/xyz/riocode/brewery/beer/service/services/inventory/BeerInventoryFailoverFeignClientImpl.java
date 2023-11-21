package xyz.riocode.brewery.beer.service.services.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import xyz.riocode.brewery.beer.service.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BeerInventoryFailoverFeignClientImpl implements BeerInventoryFeignClient {

    private final BeerInventoryFailoverFeignClient beerInventoryFailoverFeignClient;
    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return beerInventoryFailoverFeignClient.getOnHandInventory();
    }
}
