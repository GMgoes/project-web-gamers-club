package br.ufms.cpcx.api.gamersclub.controllers;


import br.ufms.cpcx.api.gamersclub.dtos.GameDto;
import br.ufms.cpcx.api.gamersclub.models.ConsoleEnum;
import br.ufms.cpcx.api.gamersclub.models.GameModel;
import br.ufms.cpcx.api.gamersclub.services.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/game")
public class GameController {
    final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> saveGame(@RequestBody @Valid GameDto gameDto) {
        if (gameService.existsByNameAndConsoleAndOwner(gameDto.getName(), gameDto.getConsole(), gameDto.getOwner())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This game already exists!");
        }

        var gameModel = new GameModel();
        BeanUtils.copyProperties(gameDto, gameModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(gameModel));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable(value = "id") Long id) {
        Optional<GameModel> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable(value = "id") Long id) {
        Optional<GameModel> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        gameService.delete(gameModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Game deleted successfully.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGame(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid GameDto gameDto) {
        Optional<GameModel> gameModelOptional = gameService.findById(id);
        if (!gameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
        }
        var gameModel = new GameModel();
        BeanUtils.copyProperties(gameDto, gameModel);
        gameModel.setId(gameModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(gameService.save(gameModel));
    }


    @GetMapping
    public ResponseEntity<Page<GameModel>> getAllGames(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll(pageable));
    }

    @GetMapping("/search/{console}")
    public ResponseEntity<Page<GameModel>> findGamesByConsoleAndFilters(@PathVariable ConsoleEnum console,
                                                                        @RequestParam(required = false) String name,
                                                                        @RequestParam(required = false) String owner,
                                                                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        var gameDto = new GameDto();
        gameDto.setConsole(console);
        gameDto.setName(name);
        gameDto.setOwner(owner);

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByConsoleAndFilter(gameDto, pageable));
    }
}
