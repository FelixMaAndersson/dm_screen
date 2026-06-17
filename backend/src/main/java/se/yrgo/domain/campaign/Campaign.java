package se.yrgo.domain.campaign;

import jakarta.persistence.*;
import se.yrgo.domain.Encounter;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.domain.User;

import java.util.Set;


@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "dm_id")
    private User dm;

    @Embedded
    private CampaignDate currentDate;

    @OneToMany(mappedBy = "campaign")
    private Set<PlayerCharacter> character;

    @OneToMany(mappedBy = "campaign")
    private Set<Encounter> encounters;

    public Campaign(String name, User dm, CampaignDate currentDate) {
        this.name = name;
        this.dm = dm;
        this.currentDate = currentDate;
    }

    public Campaign() {

    }

    public Campaign(String name, User dm) {
        this.name = name;
        this.dm = dm;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getDm() {
        return dm;
    }

    public void setDm(User dm) {
        this.dm = dm;
    }

    public CampaignDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(CampaignDate currentDate) {
        this.currentDate = currentDate;
    }

    public Set<PlayerCharacter> getCharacter() {
        return character;
    }

    public void setCharacter(Set<PlayerCharacter> character) {
        this.character = character;
    }

    public Set<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(Set<Encounter> encounters) {
        this.encounters = encounters;
    }

    public Long getId() {
        return id;
    }
}
