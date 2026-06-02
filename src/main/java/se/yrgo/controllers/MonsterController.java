package se.yrgo.controllers;

import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Monster;
import se.yrgo.dto.monster.CreateMonsterRequest;
import se.yrgo.dto.monster.MonsterResponse;
import se.yrgo.dto.monster.UpdateMonsterRequest;
import se.yrgo.exceptions.MonsterNotFoundException;
import se.yrgo.services.monster.MonsterService;

import java.util.List;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {

    private final MonsterService service;

    public MonsterController(MonsterService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public MonsterResponse createMonster(
            @RequestBody CreateMonsterRequest request
    ) {
        Monster monster = service.createMonster(request);
        return new MonsterResponse(
                monster.getId(),
                monster.getName(),
                monster.getCr(),
                monster.getType(),
                monster.getSize(),
                monster.getAlignment(),
                monster.getHabitat(),
                monster.getTags()
        );
    }

    // READ

    @GetMapping
    public List<MonsterResponse> getAllMonsters() {
        return service.getAllMonsters()
                .stream()
                .map(monster -> new MonsterResponse(
                        monster.getId(),
                        monster.getName(),
                        monster.getCr(),
                        monster.getType(),
                        monster.getSize(),
                        monster.getAlignment(),
                        monster.getHabitat(),
                        monster.getTags()
                ))
                .toList();
    }

    // UPDATE

    @PutMapping
    public MonsterResponse updateMonster(
            @PathVariable Long id,
            @RequestBody UpdateMonsterRequest request
    ) throws MonsterNotFoundException {
        Monster updatedMonster = service.updateMonster(
                id,
                request
        );
        return new MonsterResponse(
                updatedMonster.getId(),
                updatedMonster.getName(),
                updatedMonster.getCr(),
                updatedMonster.getType(),
                updatedMonster.getSize(),
                updatedMonster.getAlignment(),
                updatedMonster.getHabitat(),
                updatedMonster.getTags()
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void deleteMonster(@PathVariable Long id) {
     service.deleteMonster(id);
    }


}
