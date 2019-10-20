package com.kian.game.validate;

import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.Pit;
import com.kian.game.enums.GameStatus;
import com.kian.game.enums.PitType;
import com.kian.game.enums.PlayerId;

public class PitValidator implements Validator {

    @Override
    public ValidationError validate(Mancala mancala, Pit pit, PlayerId turn) {

        if (mancala.getStatus() == GameStatus.GAME_FINISHED) {
            return ValidationError.GAME_FINISHED;
        } else if (pit == null) {
            return ValidationError.INVALID_PIT;
        } else if (pit.getOwner() != turn) {
            return ValidationError.WRONG_PLAYER_TURN;
        } else if (pit.getType() != PitType.NORMAL_PIT) {
            return ValidationError.WRONG_PIT_SELECTED;
        } else if (pit.getStoneCount() == 0) {
            return ValidationError.EMPTY_PIT_SELECTED;
        }

        return null;
    }

}
