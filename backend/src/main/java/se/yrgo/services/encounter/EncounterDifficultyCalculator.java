package se.yrgo.services.encounter;

import org.springframework.stereotype.Service;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.EncounterMonster;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.domain.enums.Difficulty;

@Service
public class EncounterDifficultyCalculator {

    private static final int[][] XP_BUDGETS = {
            // level:    1      2      3      4      5      6      7      8      9      10     11     12     13     14     15     16     17     18     19     20
            /* LOW */      {50,   100,   150,   250,   500,   600,   750,  1000,  1300,  1600,  1900,  2200,  2600,  2900,  3300,  3800,  4500,  5000,  5500,  6400},
            /* MODERATE */ {75,   150,   225,   375,   750,  1000,  1300,  1700,  2000,  2300,  2900,  3700,  4200,  4900,  5400,  6100,  7200,  8700, 10700, 13200},
            /* HIGH */     {100,  200,   400,   500,  1100,  1400,  1700,  2100,  2600,  3100,  4100,  4700,  5400,  6200,  7800,  9800, 11700, 14200, 17200, 22000}
    };

    public Difficulty calculate(Encounter encounter) {
        int monsterXp = calculateMonsterXp(encounter);

        int lowBudget = calculateBudget(encounter, BudgetLevel.LOW);
        int moderateBudget = calculateBudget(encounter, BudgetLevel.MODERATE);
        int highBudget = calculateBudget(encounter, BudgetLevel.HIGH);

        if (monsterXp < lowBudget) {
            return Difficulty.TRIVIAL;
        }

        if (monsterXp < moderateBudget) {
            return Difficulty.EASY;
        }

        if (monsterXp < highBudget) {
            return Difficulty.MEDIUM;
        }

        if (monsterXp == highBudget) {
            return Difficulty.HARD;
        }

        return Difficulty.DEADLY;
    }

    public boolean isWithinDifficulty(Encounter encounter, Difficulty targetDifficulty) {
        return calculate(encounter) == targetDifficulty;
    }

    public int calculateMonsterXp(Encounter encounter) {
        return encounter.getEncounterMonsters()
                .stream()
                .mapToInt(this::xpForEncounterMonster)
                .sum();
    }

    private int xpForEncounterMonster(EncounterMonster encounterMonster) {
        int baseXp = encounterMonster.getMonster().getXp();

        if (encounterMonster.isBoss()) {
            return (int) Math.round(baseXp * 1.5);
        }

        return baseXp;
    }

    private int calculateBudget(Encounter encounter, BudgetLevel budgetLevel) {
        return encounter.getPlayerCharacters()
                .stream()
                .mapToInt(pc -> budgetForCharacter(pc, budgetLevel))
                .sum();
    }

    private int budgetForCharacter(PlayerCharacter pc, BudgetLevel budgetLevel) {
        int level = pc.getLevel();

        if (level < 1 || level > 20) {
            throw new IllegalArgumentException("Player character level must be between 1 and 20.");
        }

        int levelIndex = level - 1;

        return switch (budgetLevel) {
            case LOW -> XP_BUDGETS[0][levelIndex];
            case MODERATE -> XP_BUDGETS[1][levelIndex];
            case HIGH -> XP_BUDGETS[2][levelIndex];
        };
    }

    private enum BudgetLevel {
        LOW,
        MODERATE,
        HIGH
    }
}