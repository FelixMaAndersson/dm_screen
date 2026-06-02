package se.yrgo.domain;

import jakarta.persistence.*;
import se.yrgo.domain.enums.Difficulty;
import se.yrgo.domain.enums.Habitat;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Habitat habitat;
    private int encounterDistance;

    @OneToMany(mappedBy = "encounter")
    private Set<EncounterMonster> encounterMonsters = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Difficulty encounterDifficulty;

    @ManyToMany
    @JoinTable(
            name = "encounter_player_character",
            joinColumns = @JoinColumn(name = "encounter_id"),
            inverseJoinColumns = @JoinColumn(name = "player_character_id")
    )
    private Set<PlayerCharacter> playerCharacters = new HashSet<>();

    private String lore;

    public Encounter(Habitat habitat, int encounterDistance, Set<EncounterMonster> encounterMonsters, Difficulty encounterDifficulty, Set<PlayerCharacter> playerCharacters, String lore) {
        this.habitat = habitat;
        this.encounterDistance = encounterDistance;
        this.encounterMonsters = encounterMonsters;
        this.encounterDifficulty = encounterDifficulty;
        this.playerCharacters = playerCharacters;
        this.lore = lore;
    }

    public Encounter() {

    }

    public Long getId() {
        return id;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public int getEncounterDistance() {
        return encounterDistance;
    }

    public void setEncounterDistance(int encounterDistance) {
        this.encounterDistance = encounterDistance;
    }

    public Set<EncounterMonster> getEncounterMonsters() {
        return encounterMonsters;
    }

    public void setEncounterMonsters(Set<EncounterMonster> encounterMonsters) {
        this.encounterMonsters = encounterMonsters;
    }

    public Difficulty getEncounterDifficulty() {
        return encounterDifficulty;
    }

    public void setEncounterDifficulty(Difficulty encounterDifficulty) {
        this.encounterDifficulty = encounterDifficulty;
    }

    public Set<PlayerCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(Set<PlayerCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }
}
