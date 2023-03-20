package ca.tetervak.superheroes.init;

import ca.tetervak.superheroes.service.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {


    private HeroService heroService;

    void loadInitData(){



    }

}
