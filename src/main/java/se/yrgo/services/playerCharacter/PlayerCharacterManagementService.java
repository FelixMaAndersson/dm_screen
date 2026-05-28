package se.yrgo.services.playerCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.PlayerCharacterRepository;
import se.yrgo.domain.*;

/**
 * Service class for player-related business logic.
 *
 * This class handles creating, updating, retrieving and deleting players.
 * It also validates player data before it is saved or updated.
 *
 * Database operations are delegated to PlayerDao.
 */
@Service
public class PlayerCharacterManagementService {

    private final PlayerCharacterRepository dao;

    @Autowired
    public PlayerCharacterManagementService(PlayerCharacterRepository dao) {
        this.dao = dao;
    }

    @Transactional
    public PlayerCharacter createCharacter(User user, String name, CharacterRace race, CharacterClass characterClass, int level, Campaign campaign) {

        PlayerCharacter character = new PlayerCharacter(
                user,
                name,
                race,
                characterClass,
                level,
                campaign
        );

    //    dao.create(character);

        return character;
    }

//    @Transactional(readOnly = true)
//    public User getCharacterById(int playerId) throws UserNotFoundException {
//        // return dao.getById(playerId);
//    }

}