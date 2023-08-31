package br.ufms.cpcx.api.gamersclub.services;

import br.ufms.cpcx.api.gamersclub.dtos.GameDto;
import br.ufms.cpcx.api.gamersclub.models.ConsoleEnum;
import br.ufms.cpcx.api.gamersclub.models.GameModel;
import br.ufms.cpcx.api.gamersclub.repositories.GameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class GameService {

    final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public GameModel save(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }

    @Transactional
    public void delete(GameModel gameModel) {
        gameRepository.delete(gameModel);
    }

    public Page<GameModel> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    public Optional<GameModel> findById(Long id) {
        return gameRepository.findById(id);
    }

    public boolean existsByNameAndConsoleAndOwner(String name, ConsoleEnum consoleEnum, String owner) {
        return gameRepository.existsByNameAndConsoleAndOwner(name, consoleEnum, owner);
    }

    /***
     * Returns games by console and optional ignore case: {name with statrtsWith and owner with contains str}
     * @param gameDto
     * @param pageable
     * @return
     */
    public Page<GameModel> findByConsoleAndFilter(GameDto gameDto, Pageable pageable) {
        var gameModel = new GameModel();
        BeanUtils.copyProperties(gameDto, gameModel);

        var matcher = ExampleMatcher.matching()
                .withMatcher("console", match -> match.exact())
                .withMatcher("name", match -> match.startsWith())
                .withMatcher("owner", match -> match.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        var example = Example.of(gameModel, matcher);

        return gameRepository.findAll(example, pageable);
    }

}
