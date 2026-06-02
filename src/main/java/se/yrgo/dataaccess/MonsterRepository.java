package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.Monster;
import se.yrgo.domain.enums.*;

import java.util.List;
import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    Optional<Monster> findByName(String name);

    List<Monster> findByCr(ChallengeRating cr);

    List<Monster> findByType(MonsterType type);

    List<Monster> findBySize(CreatureSize size);

    List<Monster> findByAlignment(Alignment alignment);

    List<Monster> findByHabitat(Habitat habitat);

}
