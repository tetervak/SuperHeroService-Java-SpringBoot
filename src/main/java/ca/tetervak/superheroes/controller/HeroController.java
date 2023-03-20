package ca.tetervak.superheroes.controller;

import ca.tetervak.superheroes.dto.HeroDto;
import ca.tetervak.superheroes.service.HeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/heroes")
public class HeroController {
    private final HeroService service;

    @GetMapping
    public List<HeroDto> getHeroes() {
        return service.findAllHeroes();
    }

    @GetMapping("/{id}")
    public HeroDto getHeroById(@PathVariable("id") UUID id) {
        return service.findHeroById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHeroById(@PathVariable("id") UUID id) {
        service.removeHeroById(id);
    }

    @PostMapping
    public HeroDto postHero(@Valid @RequestBody HeroDto heroDto) {
        return service.addHero(heroDto);
    }

    @PutMapping("/{id}")
    public void putHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody HeroDto heroDto
    ) {
        if (!id.equals(heroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        service.updateHero(id, heroDto);
    }
}
