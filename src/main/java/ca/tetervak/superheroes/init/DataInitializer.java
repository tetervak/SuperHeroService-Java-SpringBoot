package ca.tetervak.superheroes.init;

import ca.tetervak.superheroes.service.HeroService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {


    private final HeroService heroService;

    public DataInitializer(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostConstruct
    void loadInitData(){




    }

}
