package ca.tetervak.superheroes.controller;

import ca.tetervak.superheroes.dto.HeroDto;
import ca.tetervak.superheroes.entity.HeroEntity;
import ca.tetervak.superheroes.service.HeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class HeroController {
    private final HeroService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<HeroDto> getAntiHeroes() {
        // Mapstruct is another dto mapper, but it's not straight forward
        var antiHeroList = service.findAllHeroes();

        return antiHeroList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.findHeroById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeHeroById(id);
    }

    @PostMapping
    public HeroDto postAntiHero(@Valid @RequestBody HeroDto heroDto) {
        var entity = convertToEntity(heroDto);
        var antiHero = service.addHero(entity);

        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody HeroDto heroDto
    ) {
        if (!id.equals(heroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var antiHeroEntity = convertToEntity(heroDto);
        service.updateHero(id, antiHeroEntity);
    }

    private HeroDto convertToDto(HeroEntity entity) {
        return mapper.map(entity, HeroDto.class);
    }

    private HeroEntity convertToEntity(HeroDto dto) {
        return mapper.map(dto, HeroEntity.class);
    }

}
