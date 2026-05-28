package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.PlayerCharacter;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Long> {

}

