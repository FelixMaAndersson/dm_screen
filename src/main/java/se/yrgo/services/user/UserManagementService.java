package se.yrgo.services.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.UserDao;
import se.yrgo.dataaccess.PlayerCharacterDao;

/**
 * Service class for managing teams. Provides methods for creating, retrieving, updating and deleting teams,
 * as well as adding players to teams while enforcing business rules such as salary cap and maximum number of players.
 */

@Service
@Transactional
public class UserManagementService {


    private final PlayerCharacterDao playerCharacterDao;
    private final UserDao userDao;

    public UserManagementService(PlayerCharacterDao playerCharacterDao, UserDao userDao) {
        this.playerCharacterDao = playerCharacterDao;
        this.userDao = userDao;
    }

}