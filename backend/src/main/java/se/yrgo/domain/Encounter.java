package se.yrgo.domain;

import jakarta.persistence.*;
import se.yrgo.domain.campaign.Campaign;
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
    private int visionDistance;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @OneToMany(
            mappedBy = "encounter",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<EncounterMonster> encounterMonsters = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "encounter_player_character",
            joinColumns = @JoinColumn(name = "encounter_id"),
            inverseJoinColumns = @JoinColumn(name = "player_character_id")
    )
    private Set<PlayerCharacter> playerCharacters = new HashSet<>();

    private boolean completed = false;

    private String lore;

    public Encounter(Habitat habitat, int visionDistance, Campaign campaign) {
        this.habitat = habitat;
        this.visionDistance = visionDistance;
        this.campaign = campaign;
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

    public int getVisionDistance() {
        return visionDistance;
    }

    public void setVisionDistance(int visionDistance) {
        this.visionDistance = visionDistance;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Set<EncounterMonster> getEncounterMonsters() {
        return encounterMonsters;
    }

    public void setEncounterMonsters(Set<EncounterMonster> encounterMonsters) {
        this.encounterMonsters = encounterMonsters;
    }

    public void addEncounterMonster(EncounterMonster em) {
        encounterMonsters.add(em);
        em.setEncounter(this);
    }

    public void removeEncounterMonster(EncounterMonster em) {
        encounterMonsters.remove(em);
        em.setEncounter(null);
    }

    public Set<PlayerCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(Set<PlayerCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public void addPlayerCharacter(PlayerCharacter playerCharacter) {
        playerCharacters.add(playerCharacter);
    }

    public void removePlayerCharacter(PlayerCharacter playerCharacter) {
        playerCharacters.remove(playerCharacter);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }
}
