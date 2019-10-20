package com.kian.game.service;

import com.kian.game.enums.RequestStatus;
import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.MancalaEnvelope;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameServiceTest {

    @Test
    public void testCreateGame() {

        GameService service = new GameServiceImpl();
        Mancala mancalaFirstGame = service.createGame();
        assertEquals(0, mancalaFirstGame.getId());

        Mancala mancalaSecondGame = service.createGame();
        assertEquals(1, mancalaSecondGame.getId());
    }

    @Test
    public void testPlayMancala() {

        GameService service = new GameServiceImpl();
        Mancala mancala = service.createGame();
        MancalaEnvelope mancalaEnvelope = service.playMancala(0, 0);

        assertEquals(RequestStatus.OK, mancalaEnvelope.getStatus());
        assertEquals(mancala, mancalaEnvelope.getMancala());
    }
}
