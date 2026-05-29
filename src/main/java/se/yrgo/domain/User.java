package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, length = 50, unique = true)
    private String userName;
    @Column(nullable = false, length = 20)
    private String password;

    @OneToMany(mappedBy = "dm")
    private Set<Campaign> campaigns = new HashSet<>();

    public User(String userName, String password, Set<Campaign> campaigns) {
        this.userName = userName;
        this.password = password;
        this.campaigns = campaigns;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}