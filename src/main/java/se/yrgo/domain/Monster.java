package se.yrgo.domain;

import jakarta.persistence.*;
import se.yrgo.domain.enums.ChallengeRating;
import se.yrgo.domain.enums.CreatureSize;
import se.yrgo.domain.enums.Habitat;
import se.yrgo.domain.enums.MonsterType;

@Entity
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
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
    private Habitat habitat;

    private String tags;

    public Monster(String name, ChallengeRating cr, MonsterType type, CreatureSize size, Habitat habitat, String tags) {
        this.name = name;
        this.cr = cr;
        this.type = type;
        this.size = size;
        this.habitat = habitat;
        this.tags = tags;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
