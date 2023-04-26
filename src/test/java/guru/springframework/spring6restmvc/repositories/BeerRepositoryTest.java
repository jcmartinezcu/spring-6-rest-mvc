package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entity.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSavedBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class, ()->{
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer 012345678901234567890123456789012345678901234567890123456789")
                    .beerStyle(BeerStyle.ALE)
                    .upc("23232323232")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush();
        });

    }

    @Test
    void testSavedBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle(BeerStyle.ALE)
                        .upc("23232323232")
                        .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}