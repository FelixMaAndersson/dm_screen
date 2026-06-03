package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.EncounterMonster;

import java.util.List;

public interface EncounterMonsterRepository extends JpaRepository<EncounterMonster, Long> {

    List<EncounterMonster> findByEncounterId(Long encounterId);
}
