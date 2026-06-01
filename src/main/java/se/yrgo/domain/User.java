package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false, length = 20)
    private String password;

    @OneToMany(mappedBy = "dm")
    private Set<Campaign> campaigns = new HashSet<>();

    public User(String name, String password, Set<Campaign> campaigns) {
        this.name = name;
        this.password = password;
        this.campaigns = campaigns;
    }

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Long getId() {
        return id;
    }
}