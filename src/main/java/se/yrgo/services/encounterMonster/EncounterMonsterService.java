package se.yrgo.services.encounterMonster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.EncounterMonsterRepository;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.dto.EncounterMonster.*;
import se.yrgo.exceptions.EncounterMonsterNotFoundException;

import java.util.List;

@Service
@Transactional
public class EncounterMonsterService {

    private final EncounterMonsterRepository repository;

    @Autowired
    public EncounterMonsterService(EncounterMonsterRepository repository) {
        this.repository = repository;
    }

    // CREATE

    public EncounterMonster createEncounterMonster(CreateEncounterMonsterRequest request) {
        EncounterMonster encounterMonster = new EncounterMonster(
                request.monster(),
                request.encounter(),
                request.currentHp(),
                request.alive(),
                request.boss(),
                request.notes()
        );

        return repository.save(encounterMonster);

    }

    // READ

    public EncounterMonster getEncounterMonsterById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterMonsterNotFoundException(id));
    }

    public List<EncounterMonster> getAllEncounterMonsters() {
        return repository.findAll();
    }

    public List<EncounterMonster> getEncounterMonstersByEncounterId(Long encounterId) {
        return repository.findByEncounterId(encounterId);
    }

    // UPDATE
    public EncounterMonster updateEncounterMonster(Long id, UpdateEncounterMonsterRequest request) {

        EncounterMonster encounterMonster = repository.findById(id)
                .orElseThrow(() -> new EncounterMonsterNotFoundException(id));

        encounterMonster.setMonster(request.monster());
        encounterMonster.setEncounter(request.encounter());
        encounterMonster.setCurrentHp(request.currentHp());
        encounterMonster.setAlive(request.alive());
        encounterMonster.setBoss(request.boss());
        encounterMonster.setNotes(request.notes());

        return repository.save(encounterMonster);
    }

    public EncounterMonster updateHp(Long id, UpdateHpRequest request) {
        EncounterMonster monster = getEncounterMonsterById(id);
        monster.setCurrentHp(request.currentHp());

        if (request.currentHp() <= 0) {
            monster.setAlive(false);
        }

        return repository.save(monster);
    }

    public EncounterMonster updateAlive(Long id, UpdateAliveRequest request) {

        EncounterMonster monster = getEncounterMonsterById(id);

        monster.setAlive(request.alive());

        return repository.save(monster);
    }

    public EncounterMonster updateNotes(
            Long id,
            UpdateNotesRequest request) {

        EncounterMonster monster = getEncounterMonsterById(id);

        monster.setNotes(request.notes());

        return repository.save(monster);
    }
}
