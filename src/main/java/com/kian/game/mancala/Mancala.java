package com.kian.game.mancala;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kian.game.player.Player;
import com.kian.game.enums.GameResult;
import com.kian.game.enums.GameStatus;
import com.kian.game.enums.PitType;
import com.kian.game.enums.PlayerId;
import com.kian.game.validate.PitValidator;
import com.kian.game.validate.ValidationError;
import com.kian.game.validate.Validator;
import lombok.Getter;

@Getter
public class Mancala {

    private int id;
    private Player player1;
    private Player player2;
    private Board board;
    private PlayerId turn;

    private GameStatus status;
    private GameResult gameResult;

    @JsonIgnore
    private Validator validator;

    public Mancala(int id, Player p1, Player p2) {
        this.id = id;
        this.player1 = p1;
        this.player2 = p2;
        this.board = new Board();
        turn = PlayerId.PLAYER_1;
        status = GameStatus.PLAYING;
        validator = new PitValidator();
    }

    public ValidationError playGame(int pitId) {
        Pit pit = board.getPit(pitId);

        ValidationError error = validator.validate(this, pit, turn);
        if (error != null) {
            return error;
        }

        applyGameRules(pit);
        return null;
    }

    private void applyGameRules(Pit pit) {
        distributeStonesRule(pit);
        capturingStonesRule(pit);
        endGameRule(pit);

        if (board.getLastUpdatedPit().getType() != PitType.BIG_PIT) {
            turn = pit.getOwner().nextTurn();
        }
    }

    private void distributeStonesRule(Pit pit) {

        int stoneCount = pit.getStoneCount();
        pit.setStoneCount(0);

        Pit nextPit = pit;
        while (stoneCount > 0) {
            nextPit = board.getNextPit(nextPit);

            if (nextPit.isValidPitForStoneDistribution(pit.getOwner())) {
                nextPit.addStone(1);
                stoneCount--;
            }
        }

        board.setLastUpdatedPit(nextPit);
    }

    private void capturingStonesRule(Pit pit) {
        Pit lastUpdatedPit = board.getLastUpdatedPit();

        if (lastUpdatedPit.getType() != PitType.BIG_PIT && pit.getOwner() == lastUpdatedPit.getOwner()
                && lastUpdatedPit.getStoneCount() == 1) {
            Pit oppositePit = board.getOppositePit(lastUpdatedPit);
            if (oppositePit.getStoneCount() > 0) {
                Pit bigPit = board.getBigPit(pit.getOwner());
                bigPit.addStone(oppositePit.getStoneCount() + 1);
                oppositePit.setStoneCount(0);
                lastUpdatedPit.setStoneCount(0);
            }
        }
    }

    private void endGameRule(Pit pit) {
        int player1RegularPitTotal = board.getPlayer1RegularPitTotal();
        int player2RegularPitTotal = board.getPlayer2RegularPitTotal();

        if (player1RegularPitTotal == 0 || player2RegularPitTotal == 0) {

            Pit player1BigPit = board.getBigPit(PlayerId.PLAYER_1);
            int player1Total = player1RegularPitTotal + player1BigPit.getStoneCount();
            player1BigPit.setStoneCount(player1Total);

            Pit player2BigPit = board.getBigPit(PlayerId.PLAYER_2);
            int player2Total = player2RegularPitTotal + player2BigPit.getStoneCount();
            player2BigPit.setStoneCount(player2Total);

            setWinner(player1Total, player2Total);
            board.resetRegularPits();
            status = GameStatus.GAME_FINISHED;
        }
    }

    private void setWinner(int player1Total, int player2Total) {
        if (player1Total > player2Total) {
            gameResult = GameResult.PLAYER_1;
        } else if (player2Total > player1Total) {
            gameResult = GameResult.PLAYER_2;
        } else {
            gameResult = GameResult.EQUAL;
        }
    }

}
