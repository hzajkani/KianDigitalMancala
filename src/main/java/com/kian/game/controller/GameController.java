package com.kian.game.controller;

import com.kian.game.mancala.MancalaEnvelope;
import com.kian.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PutMapping("/games/{gameId}/pits/{pitId}")
    public ResponseEntity<MancalaEnvelope> playGame(@PathVariable int gameId, @PathVariable int pitId) {
        return ResponseEntity.ok(gameService.playMancala(gameId, pitId));
    }
}
