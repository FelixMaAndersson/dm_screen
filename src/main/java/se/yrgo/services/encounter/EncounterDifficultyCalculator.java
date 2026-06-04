package se.yrgo.services.encounter;

import org.springframework.stereotype.Service;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.enums.Difficulty;

@Service
public class EncounterDifficultyCalculator {

    public Difficulty calculate(Encounter encounter) {
        int monsterCount = encounter.getEncounterMonsters().size();
        int pcCount = encounter.getPlayerCharacters().size();

        if (monsterCount == 0 || pcCount == 0) {
            return Difficulty.TRIVIAL;
        }

        // tillfällig förenklad logik
        if (monsterCount < pcCount) {
            return Difficulty.EASY;
        } else if (monsterCount == pcCount) {
            return Difficulty.MEDIUM;
        } else if (monsterCount <= pcCount * 2) {
            return Difficulty.HARD;
        } else {
            return Difficulty.DEADLY;
        }
    }
}
