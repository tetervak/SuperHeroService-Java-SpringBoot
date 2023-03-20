package ca.tetervak.superheroes.repository;

import ca.tetervak.superheroes.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {
}
