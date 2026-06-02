package se.yrgo.services.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.MonsterRepository;
import se.yrgo.domain.Monster;
import se.yrgo.dto.monster.CreateMonsterCharacterRequest;

@Service
public class MonsterService {

    private final MonsterRepository repository;

    @Autowired
    public MonsterService(MonsterRepository repository) {
        this.repository = repository;
    }

    public Monster createMonster(CreateMonsterCharacterRequest request) {

        Monster monster = new Monster(
                request.name(),
                request.cr(),
                request.type(),
                request.size(),
                request.habitat(),
                request.tags()
        );

        return repository.save(monster);
    }

}
