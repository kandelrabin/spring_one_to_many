package com.bnta.word_guesser.controllers;

import com.bnta.word_guesser.models.Game;
import com.bnta.word_guesser.models.Guess;
import com.bnta.word_guesser.models.Reply;
import com.bnta.word_guesser.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping
    public ResponseEntity<Reply> newGame(){
        Reply reply = gameService.startNewGame();
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reply> getGameStatus(@PathVariable long id){

        Optional<Game> game  = gameService.getGameById(id);

        if (game.isEmpty()){
            Reply reply = new Reply(
                    null,
                    "Game not found",
                    false
            );
            return new ResponseEntity<>(reply, HttpStatus.NOT_FOUND);
        } else {
            Reply reply = new Reply(
                    game.get().getCurrentState(),
                    "Game in progress",
                    false
            );
            return new ResponseEntity<>(reply, HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Reply> handleGuess(@RequestBody Guess guess,
                                             @PathVariable long id){
        Reply reply = gameService.processGuess(guess, id);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

}
