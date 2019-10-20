package com.kian.game.service;

import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.MancalaEnvelope;

public interface GameService {

    Mancala createGame();

    MancalaEnvelope playMancala(int gameId, int pitId);
}