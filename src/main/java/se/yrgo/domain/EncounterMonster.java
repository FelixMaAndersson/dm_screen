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

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

}
