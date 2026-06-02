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
    private boolean bbeg;
    private String lore;

    public EncounterMonster(Monster monster, Encounter encounter, int currentHp, boolean alive, boolean bbeg, String lore) {
        this.monster = monster;
        this.encounter = encounter;
        this.currentHp = currentHp;
        this.alive = alive;
        this.bbeg = bbeg;
        this.lore = lore;
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

    public boolean isBbeg() {
        return bbeg;
    }

    public void setBbeg(boolean bbeg) {
        this.bbeg = bbeg;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

}
