package xyz.riocode.beer.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.beer.service.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
