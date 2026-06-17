package se.yrgo.services.encounterMonster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.EncounterMonsterRepository;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.dto.encounterMonster.*;
import se.yrgo.exceptions.encounterMonster.EncounterMonsterNotFoundException;

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

    public EncounterMonsterResponse createEncounterMonster(CreateEncounterMonsterRequest request) {
        EncounterMonster encounterMonster = new EncounterMonster(
                request.monster(),
                request.encounter(),
                request.currentHp(),
                request.alive(),
                request.boss(),
                request.notes()
        );

       return toResponse(repository.save(encounterMonster));

    }

    // READ

    public EncounterMonsterResponse getEncounterMonsterById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public List<EncounterMonsterResponse> getAllEncounterMonsters() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<EncounterMonsterResponse> getEncounterMonstersByEncounterId(Long encounterId) {
        return repository.findByEncounterId(encounterId)
                .stream()
                .map(this::toResponse)
                .toList();
    }





    // UPDATE
    public EncounterMonsterResponse updateEncounterMonster(Long id, UpdateEncounterMonsterRequest request) {

        EncounterMonster encounterMonster = getOrThrow(id);

        encounterMonster.setMonster(request.monster());
        encounterMonster.setEncounter(request.encounter());
        encounterMonster.setCurrentHp(request.currentHp());
        encounterMonster.setAlive(request.alive());
        encounterMonster.setBoss(request.boss());
        encounterMonster.setNotes(request.notes());

        return toResponse(encounterMonster);
    }

    public EncounterMonsterResponse updateHp(Long id, UpdateHpRequest request) {
        EncounterMonster encounterMonster = getOrThrow(id);

        encounterMonster.setCurrentHp(request.currentHp());

        if (request.currentHp() <= 0) {
            encounterMonster.setAlive(false);
        }

        return toResponse(encounterMonster);
    }

    public EncounterMonsterResponse updateAlive(Long id, UpdateAliveRequest request) {

        EncounterMonster encounterMonster = getOrThrow(id);

        encounterMonster.setAlive(request.alive());

        return toResponse(encounterMonster);
    }

    public EncounterMonsterResponse updateNotes(
            Long id,
            UpdateNotesRequest request) {

        EncounterMonster encounterMonster = getOrThrow(id);

        encounterMonster.setNotes(request.notes());

        return toResponse(encounterMonster);
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

    private EncounterMonsterResponse toResponse(EncounterMonster em) {
        return new EncounterMonsterResponse(
                em.getId(),
                em.getMonster().getId(),
                em.getMonster().getName(),
                em.getEncounter().getId(),
                em.getCurrentHp(),
                em.isAlive(),
                em.isBoss(),
                em.getNotes()
        );
    }
}
