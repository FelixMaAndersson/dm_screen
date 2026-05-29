package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.PlayerCharacter;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerCharacter, Long> {

    Optional<PlayerCharacter> findByCharacterName(String name);
}

