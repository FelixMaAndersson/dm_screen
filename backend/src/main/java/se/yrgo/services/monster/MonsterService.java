package se.yrgo.services.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.MonsterRepository;
import se.yrgo.domain.Monster;
import se.yrgo.domain.enums.*;
import se.yrgo.dto.monster.CreateMonsterRequest;
import se.yrgo.dto.monster.MonsterResponse;
import se.yrgo.dto.monster.UpdateMonsterRequest;
import se.yrgo.exceptions.monster.MonsterNotFoundException;
import se.yrgo.exceptions.user.UserAlreadyExistsException;

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

    public MonsterResponse createMonster(CreateMonsterRequest request) {

        if (repository.existsByName(request.name())) {
            throw new UserAlreadyExistsException(
                    "Monster with name '" + request.name() + "' already exists");
        }

        Monster monster = new Monster(
                request.name(),
                request.cr(),
                request.xp(),
                request.type(),
                request.size(),
                request.habitat(),
                request.alignment(),
                request.hp(),
                request.tags()
        );

        return toResponse(repository.save(monster));
    }

    // READ

    public List<MonsterResponse> getAllMonsters() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MonsterResponse getMonsterById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id)));
    }

    public MonsterResponse getMonstersByName(String name) {
        return toResponse(repository.findByName(name).orElseThrow(() -> new MonsterNotFoundException(name)));
    }

    public List<MonsterResponse> getMonstersByCr(ChallengeRating cr) {
        return repository.findByCr(cr)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MonsterResponse> getMonsterByXp(int xp) {
        return repository.findByXp(xp)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MonsterResponse> getMonstersByType(MonsterType type) {
        return repository.findByType(type)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MonsterResponse> getMonstersBySize(CreatureSize size) {
        return repository.findBySize(size)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MonsterResponse> getMonstersByAlignment(Alignment alignment) {
        return repository.findByAlignment(alignment)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MonsterResponse> getMonstersByHabitat(Habitat habitat) {
        return repository.findByHabitat(habitat)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // UPDATE

    public MonsterResponse updateMonster(Long id, UpdateMonsterRequest request) {
        Monster monster = repository.findById(id)
                .orElseThrow(() -> new MonsterNotFoundException(id));

        monster.setName(request.name());
        monster.setCr(request.cr());
        monster.setXp(request.xp());
        monster.setType(request.type());
        monster.setSize(request.size());
        monster.setAlignment(request.alignment());
        monster.setHabitat(request.habitat());
        monster.setHp(request.hp());
        monster.setTags(request.tags());

        return toResponse(repository.save(monster));
    }

    // DELETE

    public void deleteMonster(Long id) throws MonsterNotFoundException {
        repository.deleteById(id);
    }

    // HELP METHODS

    private MonsterResponse toResponse(Monster monster) {
        return new MonsterResponse(
                monster.getId(),
                monster.getName(),
                monster.getCr(),
                monster.getType(),
                monster.getSize(),
                monster.getAlignment(),
                monster.getHabitat(),
                monster.getTags()
        );
    }
}
