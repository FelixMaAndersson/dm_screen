package se.yrgo.dataaccess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.PlayerCharacter;
import se.yrgo.exceptions.CharacterNotFoundException;

/**
 * Implementation of the TeamDao interface using JPA. Provides methods for creating, retrieving, updating and deleting teams in the database.
 */

@Repository
public class PlayerCharacterDaoImpl implements PlayerCharacterDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createCharacter(PlayerCharacter playerCharacter) {

    }

    @Override
    public void updateCharacter(PlayerCharacter playerCharacter) throws CharacterNotFoundException {

    }

    @Override
    public void deleteCharacter(PlayerCharacter playerCharacter) throws CharacterNotFoundException {

    }

    @Override
    public PlayerCharacter getCharacterById(int id) throws CharacterNotFoundException {
        return null;
    }

    @Override
    public PlayerCharacter getCharacterByName(String name) throws CharacterNotFoundException {
        return null;
    }
}