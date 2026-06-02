package se.yrgo.services.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.MonsterRepository;
import se.yrgo.domain.Monster;
import se.yrgo.domain.enums.*;
import se.yrgo.dto.monster.CreateMonsterRequest;
import se.yrgo.dto.monster.UpdateMonsterRequest;
import se.yrgo.exceptions.MonsterNotFoundException;

import java.util.List;

@Service
@Transactional
public class MonsterService {

    private final MonsterRepository repository;

    @Autowired
    public MonsterService(MonsterRepository repository) {
        this.repository = repository;
    }

    // CREATE

    public Monster createMonster(CreateMonsterRequest request) {

        Monster monster = new Monster(
                request.name(),
                request.cr(),
                request.type(),
                request.size(),
                request.habitat(),
                request.alignment(),
                request.tags()
        );

        return repository.save(monster);
    }

    // READ

    public Monster getMonsterById(Long id) throws MonsterNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new MonsterNotFoundException(id));
    }

    public List<Monster> getMonstersByName(String name) {
        return repository.findByName(name);
    }

    public List<Monster> getMonstersByCr(ChallengeRating cr) {
        return repository.findByCr(cr);
    }

    public List<Monster> getMonstersByType(MonsterType type) {
        return repository.findByType(type);
    }

    public List<Monster> getMonstersBySize(CreatureSize size) {
        return repository.findBySize(size);
    }

    public List<Monster> getMonstersByAlignment(Alignment alignment) {
        return repository.findByAlignment(alignment);
    }

    public List<Monster> getMonstersByHabitat(Habitat habitat) {
        return repository.findByHabitat(habitat);
    }

    public List<Monster> getAllMonsters() {
        return repository.findAll();
    }

    // UPDATE

    public Monster updateMonster(Long id, UpdateMonsterRequest request) {
        Monster monster = repository.findById(id)
                .orElseThrow(() -> new MonsterNotFoundException(id));

        monster.setName(request.name());
        monster.setCr(request.cr());
        monster.setType(request.type());
        monster.setSize(request.size());
        monster.setAlignment(request.alignment());
        monster.setHabitat(request.habitat());
        monster.setTags(request.tags());

        return repository.save(monster);
    }

    // DELETE

    public void deleteMonster(Long id) throws MonsterNotFoundException {
        repository.deleteById(id);
    }
}
