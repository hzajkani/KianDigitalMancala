package com.kian.game.mancala;

import com.kian.game.enums.PitType;
import com.kian.game.enums.PlayerId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pit {

    private int id;
    private PitType type;
    private PlayerId owner;
    private int stoneCount;

    public void addStone(int increment) {
        this.stoneCount += increment;
    }

    public boolean isValidPitForStoneDistribution(PlayerId stoneOwner) {
        return (type != PitType.BIG_PIT) || (owner == stoneOwner);
    }
}
