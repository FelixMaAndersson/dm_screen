package se.yrgo.domain;

import jakarta.persistence.*;


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

    public Campaign(String name, String description, User dm) {
        this.name = name;
        this.description = description;
        this.dm = dm;
    }

    public Campaign() {

    }

    public Campaign(String name, User dm) {
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
}
