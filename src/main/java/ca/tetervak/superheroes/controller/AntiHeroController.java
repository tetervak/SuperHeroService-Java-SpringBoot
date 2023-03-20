package ca.tetervak.superheroes.controller;

import ca.tetervak.superheroes.dto.AntiHeroDto;
import ca.tetervak.superheroes.entity.AntiHeroEntity;
import ca.tetervak.superheroes.service.AntiHeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class AntiHeroController {
    private final AntiHeroService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<AntiHeroDto> getAntiHeroes() {
        // Mapstruct is another dto mapper, but it's not straight forward
        var antiHeroList = StreamSupport
                .stream(service.findAllAntiHeroes().spliterator(), false)
                .collect(Collectors.toList());


        return antiHeroList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.findAntiHeroById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    @PostMapping
    public AntiHeroDto postAntiHero(@Valid @RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);
        var antiHero = service.addAntiHero(entity);

        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AntiHeroDto antiHeroDto
    ) {
        if (!id.equals(antiHeroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var antiHeroEntity = convertToEntity(antiHeroDto);
        service.updateAntiHero(id, antiHeroEntity);
    }

    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }

    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }

}
