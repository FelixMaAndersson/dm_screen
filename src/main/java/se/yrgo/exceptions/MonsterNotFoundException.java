package se.yrgo.exceptions;

import se.yrgo.domain.enums.*;

public class MonsterNotFoundException extends RuntimeException {

    public MonsterNotFoundException(Long id) {
        super("Monster with id " + id + " was not found");
    }

    public MonsterNotFoundException(String name) {
        super("Monster with name " + name + " was not found");
    }

    public MonsterNotFoundException(MonsterType type) {
        super("Monsters with type " + type + " was not found");
    }

    public MonsterNotFoundException(CreatureSize size) {
        super("Monsters with size " + size + " was not found");
    }

    public MonsterNotFoundException(ChallengeRating cr) {
        super("Monsters with challenge rating " + cr + " was not found");
    }

    public MonsterNotFoundException(Alignment alignment) {
        super("Monsters with alignment " + alignment + " was not found");
    }

    public MonsterNotFoundException(Habitat habitat) {
        super("Monsters dwelling in " + habitat + " was not found");
    }



}
