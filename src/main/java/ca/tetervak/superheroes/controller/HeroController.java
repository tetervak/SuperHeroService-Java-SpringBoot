package ca.tetervak.superheroes.controller;

import ca.tetervak.superheroes.dto.HeroDto;
import ca.tetervak.superheroes.exception.NotFoundException;
import ca.tetervak.superheroes.service.HeroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public List<HeroDto> getHeroes() {
        return heroService.findAllHeroes();
    }

    @GetMapping("/{id}")
    public HeroDto getHeroById(@PathVariable("id") UUID id) {
        return heroService.findHeroById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHeroById(@PathVariable("id") UUID id) {
        heroService.removeHeroById(id);
    }

    @PostMapping
    public HeroDto postHero(@Valid @RequestBody HeroDto heroDto) {
        return heroService.addHero(heroDto);
    }

    @PutMapping("/{id}")
    public HeroDto putHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody HeroDto heroDto
    ) {
        if (!id.equals(heroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        return heroService.updateHero(heroDto);
    }

}
