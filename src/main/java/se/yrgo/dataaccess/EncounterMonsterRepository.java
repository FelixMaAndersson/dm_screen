package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.EncounterMonster;

public interface EncounterMonsterRepository extends JpaRepository<EncounterMonster, Long> {

}
