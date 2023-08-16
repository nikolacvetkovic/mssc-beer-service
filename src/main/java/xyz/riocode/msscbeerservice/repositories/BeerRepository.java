package xyz.riocode.msscbeerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.msscbeerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
