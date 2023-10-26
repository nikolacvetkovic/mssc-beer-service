package xyz.riocode.beer.service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.common.model.BeerStyle;

import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest);
    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, PageRequest pageRequest);

    Optional<Beer> findByUpc(String upc);
}
