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


}
