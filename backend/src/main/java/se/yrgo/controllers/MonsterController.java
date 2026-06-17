package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.dataaccess.MonsterRepository;
import se.yrgo.domain.Monster;
import se.yrgo.dto.monster.CreateMonsterRequest;
import se.yrgo.dto.monster.MonsterResponse;
import se.yrgo.dto.monster.UpdateMonsterRequest;
import se.yrgo.exceptions.monster.MonsterNotFoundException;
import se.yrgo.exceptions.user.UserAlreadyExistsException;
import se.yrgo.services.monster.MonsterService;
import java.util.List;

@RestController
@RequestMapping("/api/monsters")
@CrossOrigin(origins = "http://localhost:5173")
public class MonsterController {

    private final MonsterService service;
    private final MonsterRepository repository;

    public MonsterController(MonsterService service, MonsterRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // CREATE

    @PostMapping
    public MonsterResponse createMonster(
            @RequestBody CreateMonsterRequest request
    ) {

        return service.createMonster(request);
    }

    // READ

    @GetMapping
    public List<MonsterResponse> getAllMonsters() {
        return service.getAllMonsters();
    }

    @GetMapping("/{id}")
    public MonsterResponse getMonsterById(@PathVariable Long id)
            throws MonsterNotFoundException {

        return service.getMonsterById(id);
    }

    // UPDATE

    @PutMapping("/{id}")
    public MonsterResponse updateMonster(
            @PathVariable Long id,
            @RequestBody UpdateMonsterRequest request
    ) throws MonsterNotFoundException {

        return service.updateMonster(id, request);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteMonster(@PathVariable Long id) {
     service.deleteMonster(id);
    }
}
