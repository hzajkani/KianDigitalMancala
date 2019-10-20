package com.kian.game.player;

import com.kian.game.enums.PlayerType;

public class PlayerFactory {
    public Player getPlayer(PlayerType type, String name) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case HUMAN_PLAYER:
                return new HumanPlayer(name);
            case MACHINE_USER:
                return new MachinePlayer("Machine Player");
            default:
                break;
        }
        return null;
    }
}
