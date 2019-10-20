package com.kian.game.mancala;

import com.kian.game.enums.PitType;
import com.kian.game.enums.PlayerId;
import lombok.Getter;

@Getter
public class Board {

    public static final int REGULAR_PIT_COUNT_PER_PLAYER = 6;
    public static final int INITIAL_STONE_COUNT_PER_PIT = 6;

    private int pitCount;
    private int player1BigPitIndex;
    private int player2BigPitIndex;
    private Pit[] pits;
    private Pit lastUpdatedPit;


    public Board() {
        pitCount = REGULAR_PIT_COUNT_PER_PLAYER * 2 + 2;
        player1BigPitIndex = REGULAR_PIT_COUNT_PER_PLAYER;
        player2BigPitIndex = pitCount - 1;

        pits = new Pit[pitCount];

        fillBoard();
    }


    private void fillBoard() {
        int i = 0;
        while (i < REGULAR_PIT_COUNT_PER_PLAYER) {
            pits[i] = new Pit(i, PitType.NORMAL_PIT, PlayerId.PLAYER_1, INITIAL_STONE_COUNT_PER_PIT);
            i++;
        }

        pits[i] = new Pit(i, PitType.BIG_PIT, PlayerId.PLAYER_1, 0);
        i++;

        while (i < pitCount - 1) {
            pits[i] = new Pit(i, PitType.NORMAL_PIT, PlayerId.PLAYER_2, INITIAL_STONE_COUNT_PER_PIT);
            i++;
        }

        pits[i] = new Pit(i, PitType.BIG_PIT, PlayerId.PLAYER_2, 0);
    }

    public Pit getPit(int pitId) {
        if (pitId < 0 || pitId >= pitCount) {
            return null;
        }

        return pits[pitId];
    }

    public Pit getNextPit(Pit pit) {
        int nextId = pit.getId() + 1;

        if (nextId == pitCount) {
            return pits[0];
        }

        return pits[nextId];
    }

    public Pit getOppositePit(Pit pit) {
        return pits[pitCount - 2 - pit.getId()];
    }

    public Pit getLastUpdatedPit() {
        return lastUpdatedPit;
    }

    public void setLastUpdatedPit(Pit pit) {
        lastUpdatedPit = pit;
    }

    public Pit getBigPit(PlayerId owner) {
        if (owner == PlayerId.PLAYER_1) {
            return pits[player1BigPitIndex];
        } else {
            return pits[player2BigPitIndex];
        }
    }

    public Pit getPlayer1BigPit() {
        return pits[player1BigPitIndex];
    }

    public Pit getPlayer2BigPit() {
        return pits[player2BigPitIndex];
    }

    public int getPlayer1RegularPitTotal() {
        Integer total = 0;
        for (int i = 0; i < REGULAR_PIT_COUNT_PER_PLAYER; i++) {
            total += pits[i].getStoneCount();
        }
        return total;
    }

    public int getPlayer2RegularPitTotal() {
        Integer total = 0;
        for (int i = REGULAR_PIT_COUNT_PER_PLAYER + 1; i < pitCount - 1; i++) {
            total += pits[i].getStoneCount();
        }
        return total;
    }

    public void resetRegularPits() {
        int i = 0;
        while (i < REGULAR_PIT_COUNT_PER_PLAYER) {
            pits[i].setStoneCount(0);
            i++;
        }
        i++;
        while (i < pitCount - 1) {
            pits[i].setStoneCount(0);
            i++;
        }
    }
}
