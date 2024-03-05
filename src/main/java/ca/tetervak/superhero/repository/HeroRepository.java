package ca.tetervak.superhero.repository;

import ca.tetervak.superhero.entity.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {
}
