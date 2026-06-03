package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {

}
