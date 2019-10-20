package com.kian.game.enums;

public enum PlayerId {
	PLAYER_1, PLAYER_2;

	public PlayerId nextTurn() {
		if (this == PLAYER_1) {
			return PLAYER_2;
		} else {
			return PLAYER_1;
		}
	}
}
