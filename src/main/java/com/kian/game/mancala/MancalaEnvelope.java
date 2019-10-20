package com.kian.game.mancala;

import com.kian.game.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MancalaEnvelope {

    private Mancala mancala;
    private RequestStatus status;
    private String message;

    public static final String GAME_NOT_FOUND = "Game not found.";
}
