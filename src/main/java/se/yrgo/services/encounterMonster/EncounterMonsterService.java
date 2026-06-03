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
        return getOrThrow(id);
    }

    public List<EncounterMonster> getAllEncounterMonsters() {
        return repository.findAll();
    }

    public List<EncounterMonsterResponse> getEncounterMonstersByEncounterId(Long encounterId) {
        return repository.findByEncounterId(encounterId)
                .stream()
                .map(em -> new EncounterMonsterResponse(
                        em.getId(),
                        em.getMonster().getId(),
                        em.getMonster().getName(),
                        em.getEncounter().getId(),
                        em.getCurrentHp(),
                        em.isAlive(),
                        em.isBoss(),
                        em.getNotes()
                ))
                .toList();
    }

    // UPDATE
    public void updateEncounterMonster(Long id, UpdateEncounterMonsterRequest request) {

        EncounterMonster encounterMonster = getOrThrow(id);

        encounterMonster.setMonster(request.monster());
        encounterMonster.setEncounter(request.encounter());
        encounterMonster.setCurrentHp(request.currentHp());
        encounterMonster.setAlive(request.alive());
        encounterMonster.setBoss(request.boss());
        encounterMonster.setNotes(request.notes());
    }

    public void updateHp(Long id, UpdateHpRequest request) {
        EncounterMonster monster = getOrThrow(id);

        monster.setCurrentHp(request.currentHp());

        if (request.currentHp() <= 0) {
            monster.setAlive(false);
        }

    }

    public void updateAlive(Long id, UpdateAliveRequest request) {

        EncounterMonster monster = getOrThrow(id);

        monster.setAlive(request.alive());
    }

    public void updateNotes(
            Long id,
            UpdateNotesRequest request) {

        EncounterMonster monster = getOrThrow(id);

        monster.setNotes(request.notes());
    }

    // DELETE

    public void deleteEncounterMonster(Long id) {
        EncounterMonster monster = getOrThrow(id);
        repository.delete(monster);
    }

    // HELP METHODS

    private EncounterMonster getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterMonsterNotFoundException(id));
    }
}
