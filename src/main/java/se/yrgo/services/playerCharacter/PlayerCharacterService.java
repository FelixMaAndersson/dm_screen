package se.yrgo.services.playerCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.PlayerRepository;
import se.yrgo.domain.*;
import se.yrgo.exceptions.CharacterNotFoundException;

import java.util.List;


@Service
@Transactional
public class PlayerCharacterService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerCharacterService(PlayerRepository repository) {
        this.repository = repository;
    }


    public PlayerCharacter createCharacter(User user, String name, CharacterRace race, CharacterClass characterClass, int level, Campaign campaign) {

        PlayerCharacter playerCharacter = new PlayerCharacter(
                user,
                name,
                race,
                characterClass,
                level,
                campaign
        );
        return repository.save(playerCharacter);
    }


    public PlayerCharacter getCharacterById(Long id) throws CharacterNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public PlayerCharacter getCharacterByName(String name) throws CharacterNotFoundException {
        return repository.findByCharacterName(name)
                .orElseThrow(() -> new CharacterNotFoundException(name));
    }

    public void deleteCharacter(Long id) {
        repository.deleteById(id);
    }

    public List<PlayerCharacter> getAllCharacters() {
        return repository.findAll();
    }

}