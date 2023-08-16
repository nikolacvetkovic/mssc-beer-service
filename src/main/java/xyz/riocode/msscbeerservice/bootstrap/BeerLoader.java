package xyz.riocode.msscbeerservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.riocode.msscbeerservice.domain.Beer;
import xyz.riocode.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .upc(30000001L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .upc(30000002L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
