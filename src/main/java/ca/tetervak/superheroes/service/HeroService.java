package ca.tetervak.superheroes.service;

import java.util.List;
import java.util.UUID;

import ca.tetervak.superheroes.entity.HeroEntity;
import ca.tetervak.superheroes.exception.NotFoundException;
import ca.tetervak.superheroes.repository.HeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HeroService {

    private final HeroRepository repo;

    public List<HeroEntity> findAllHeroes() {
        return repo.findAll();
    }

    public HeroEntity findHeroById(UUID id) {
        return findOrThrow(id);
    }

    public void removeHeroById(UUID id) {
        repo.deleteById(id);
    }

    public HeroEntity addHero(HeroEntity hero) {
        return repo.save(hero);
    }

    public void updateHero(UUID id, HeroEntity hero) {
        findOrThrow(id);
        repo.save(hero);
    }

    private HeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Hero by id " + id + " was not found")
                );
    }
}

