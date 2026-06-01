package se.yrgo.domain;

import jakarta.persistence.*;

@Entity
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private CharacterRace race;

    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;
    private int level;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;



    public PlayerCharacter(User user, String name, CharacterRace race, CharacterClass characterClass, int level, Campaign campaign) {
        this.user = user;
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.level = level;
        this.campaign = campaign;
    }

    public PlayerCharacter() {

    }

    public PlayerCharacter(String name, CharacterRace race, CharacterClass characterClass, int level, Campaign campaign) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.level = level;
        this.campaign = campaign;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterRace getRace() {
        return race;
    }

    public void setRace(CharacterRace race) {
        this.race = race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass chClass) {
        this.characterClass = chClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

}