package xyz.riocode.brewery.beer.service.services.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.riocode.brewery.beer.service.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Profile("local-discovery")
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeignImpl implements BeerInventoryService{

    private final BeerInventoryFeignClient beerInventoryFeignClient;
    @Override
    public Integer getOnHandInventory(UUID beerId) {
        ResponseEntity<List<BeerInventoryDto>> responseEntity = beerInventoryFeignClient.getOnHandInventory(beerId);
        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
