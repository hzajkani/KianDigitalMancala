package com.kian.game.service;

import com.kian.game.player.PlayerFactory;
import com.kian.game.mancala.Mancala;
import com.kian.game.mancala.MancalaEnvelope;
import com.kian.game.player.Player;
import com.kian.game.enums.PlayerType;
import com.kian.game.enums.RequestStatus;
import com.kian.game.validate.ValidationError;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private static final Map<Integer, Mancala> map = new HashMap<>();
    private static int gameId;
    private PlayerFactory playerFactory;

    public GameServiceImpl() {
        playerFactory = new PlayerFactory();
        gameId = 0;
    }

    @Override
    public Mancala createGame() {
        Player user1 = playerFactory.getPlayer(PlayerType.HUMAN_PLAYER, "Player 1");
        Player user2 = playerFactory.getPlayer(PlayerType.HUMAN_PLAYER, "Player 2");

        int id = getGameId();
        Mancala mancala = new Mancala(id, user1, user2);
        map.put(id, mancala);
        return mancala;
    }

    @Override
    public MancalaEnvelope playMancala(int gameId, int pitId) {
        Mancala mancala = map.get(gameId);
        if (mancala == null) {
            return new MancalaEnvelope(null, RequestStatus.FAIL, MancalaEnvelope.GAME_NOT_FOUND);
        }

        ValidationError error = mancala.playGame(pitId);
        if (error != null) {
            return new MancalaEnvelope(null, RequestStatus.FAIL, error.getText());
        }
        return new MancalaEnvelope(mancala, RequestStatus.OK, null);
    }

    private int getGameId() {
        int id;
        id = gameId++;
        return id;
    }
}
