package se.yrgo.domain;

import jakarta.persistence.*;
import se.yrgo.domain.enums.*;

@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChallengeRating cr;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MonsterType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CreatureSize size;

    @Enumerated(EnumType.STRING)
    private Alignment alignment;

    @Enumerated(EnumType.STRING)
    private Habitat habitat;

    private int hp;

    private String tags;

    public Monster(String name, ChallengeRating cr, MonsterType type, CreatureSize size, Habitat habitat, Alignment alignment, int hp, String tags) {
        this.name = name;
        this.cr = cr;
        this.type = type;
        this.size = size;
        this.habitat = habitat;
        this.alignment = alignment;
        this.hp = hp;
        this.tags = tags;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Monster() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChallengeRating getCr() {
        return cr;
    }

    public void setCr(ChallengeRating cr) {
        this.cr = cr;
    }

    public MonsterType getType() {
        return type;
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public CreatureSize getSize() {
        return size;
    }

    public void setSize(CreatureSize size) {
        this.size = size;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
