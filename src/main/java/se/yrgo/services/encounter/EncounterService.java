package se.yrgo.services.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.EncounterRepository;
import se.yrgo.domain.Encounter;

@Service
@Transactional
public class EncounterService {

    private final EncounterRepository repository;

    @Autowired
    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public Encounter createEncounter() {

    }
}
