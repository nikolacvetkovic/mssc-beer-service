package xyz.riocode.beer.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.riocode.beer.service.domain.Beer;
import xyz.riocode.beer.service.exception.NotFoundException;
import xyz.riocode.beer.service.repositories.BeerRepository;
import xyz.riocode.beer.service.web.mapper.BeerMapper;
import xyz.riocode.beer.service.web.model.BeerDto;
import xyz.riocode.beer.service.web.model.BeerPagedList;
import xyz.riocode.beer.service.web.model.BeerStyle;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        } else {
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto update(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList getBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (beerName != null && beerStyle != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if(beerName != null) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if(beerStyle != null) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage.getContent().stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
            return beerPagedList;
        } else {
            beerPagedList = new BeerPagedList(beerPage.getContent().stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(),
                            beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
            return beerPagedList;
        }
    }
}
