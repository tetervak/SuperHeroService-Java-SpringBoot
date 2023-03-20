package ca.tetervak.superheroes.repository;

import ca.tetervak.superheroes.entity.AntiHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AntiHeroRepository extends JpaRepository<AntiHeroEntity, UUID> {
}
