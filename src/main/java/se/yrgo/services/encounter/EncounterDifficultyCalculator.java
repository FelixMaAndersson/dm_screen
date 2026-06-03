package se.yrgo.services.encounter;

import org.springframework.stereotype.Service;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.enums.Difficulty;

@Service
public class EncounterDifficultyCalculator {

    public Difficulty calculate(Encounter encounter) {
        // räkna total monster XP
        // räkna multiplier beroende på antal monster
        // räkna party thresholds beroende på PCs level
        // jämför och returnera TRIVIAL/EASY/MEDIUM/HARD/DEADLY
        return null;
    }
}
