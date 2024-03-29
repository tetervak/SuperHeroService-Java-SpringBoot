package ca.tetervak.superhero.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ca.tetervak.superhero.dto.HeroDto;
import ca.tetervak.superhero.entity.HeroEntity;
import ca.tetervak.superhero.exception.NotFoundException;
import ca.tetervak.superhero.repository.HeroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    private final HeroRepository repo;

    private final ModelMapper mapper;

    public HeroService(HeroRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<HeroDto> findAllHeroes() {
        return repo.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public HeroDto findHeroById(UUID id) {
        return convertToDto(findOrThrow(id));
    }

    public void removeHeroById(UUID id) {
        repo.delete(findOrThrow(id));
    }

    public HeroDto addHero(HeroDto heroDto) {
        return convertToDto(repo.save(convertToEntity(heroDto)));
    }

    public HeroDto updateHero(HeroDto heroDto) {
        findOrThrow(heroDto.getId());
        return convertToDto(repo.save(convertToEntity(heroDto)));
    }

    public void deleteAllHeroes(){
        repo.deleteAll();
    }

    private HeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Hero by id " + id + " was not found")
                );
    }

    private HeroDto convertToDto(HeroEntity heroEntity) {
        return mapper.map(heroEntity, HeroDto.class);
    }

    private HeroEntity convertToEntity(HeroDto heroDto) {
        return mapper.map(heroDto, HeroEntity.class);
    }

}

