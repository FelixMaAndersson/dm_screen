package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.PlayerCharacter;

import java.util.List;
import java.util.Optional;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Long> {

    Optional<PlayerCharacter> findByName(String name);

    List<PlayerCharacter> findByCampaignId(Long campaignId);
}

