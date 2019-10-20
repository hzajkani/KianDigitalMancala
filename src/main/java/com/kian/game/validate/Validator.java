package com.kian.game.validate;

import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.Pit;
import com.kian.game.enums.PlayerId;

public interface Validator {
    public ValidationError validate(Mancala mancala, Pit pit, PlayerId turn);
}
