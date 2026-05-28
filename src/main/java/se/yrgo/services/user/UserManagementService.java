package se.yrgo.services.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.UserRepository;
import se.yrgo.dataaccess.PlayerCharacterRepository;

/**
 * Service class for managing teams. Provides methods for creating, retrieving, updating and deleting teams,
 * as well as adding players to teams while enforcing business rules such as salary cap and maximum number of players.
 */

@Service
@Transactional
public class UserManagementService {


    private final PlayerCharacterRepository playerCharacterRepository;
    private final UserRepository userRepository;

    public UserManagementService(PlayerCharacterRepository playerCharacterRepository, UserRepository userRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.userRepository = userRepository;
    }

}