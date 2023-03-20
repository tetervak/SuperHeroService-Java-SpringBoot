package ca.tetervak.superheroes.init;

import ca.tetervak.superheroes.dto.HeroDto;
import ca.tetervak.superheroes.service.HeroService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

    @PreDestroy
    public void clearData() {
        heroService.deleteAllHeroes();
    }

}
