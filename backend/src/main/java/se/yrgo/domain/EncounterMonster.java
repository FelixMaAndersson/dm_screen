package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class EncounterMonster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "encounter_id")
    private Encounter encounter;

    private int currentHp;
    private boolean alive;
    private boolean boss;
    private String notes;

    public EncounterMonster(Monster monster, Encounter encounter, int currentHp, boolean alive, boolean boss, String notes) {
        this.monster = monster;
        this.encounter = encounter;
        this.currentHp = currentHp;
        this.alive = alive;
        this.boss = boss;
        this.notes = notes;
    }

    public EncounterMonster() {
    }

    public Long getId() {
        return id;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
