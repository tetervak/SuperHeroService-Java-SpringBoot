package ca.tetervak.superhero.init;

import ca.tetervak.superhero.dto.HeroDto;
import ca.tetervak.superhero.service.HeroService;
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

        HeroDto superman = new HeroDto("Clark", "Kent");
        superman.setKnownAs("Superman");
        superman.setHouse("Krypton");
        heroService.addHero(superman);

        HeroDto spiderMan = new HeroDto("Peter", "Parker");
        spiderMan.setKnownAs("Spider-Man");
        spiderMan.setHouse("Earth");
        heroService.addHero(spiderMan);
    }

}
