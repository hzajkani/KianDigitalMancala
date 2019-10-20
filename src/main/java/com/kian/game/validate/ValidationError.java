package com.kian.game.validate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationError {

    WRONG_PIT_SELECTED("click on your regular pit."),
    EMPTY_PIT_SELECTED("click non-empty pit: Your non-empty regular pit."),
    WRONG_PLAYER_TURN("It's the other player's turn."),
    GAME_FINISHED("Game finished."),
    INVALID_PIT("Invalid pit Id.");

    private final String text;
}
