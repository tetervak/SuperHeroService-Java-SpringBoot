package ca.tetervak.superheroes.service;

import java.util.UUID;

import ca.tetervak.superheroes.entity.AntiHeroEntity;
import ca.tetervak.superheroes.exception.NotFoundException;
import ca.tetervak.superheroes.repository.AntiHeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AntiHeroService {

    private final AntiHeroRepository repo;

    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return repo.findAll();
    }

    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    public void removeAntiHeroById(UUID id) {
        repo.deleteById(id);
    }

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                );
    }
}

