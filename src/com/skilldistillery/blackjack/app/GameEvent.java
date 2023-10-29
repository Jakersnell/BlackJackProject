package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.BlackJackPlayer;

public enum GameEvent {
	START, RESTART, QUIT, PLAYERTURN, PLAYERBUST, PLAYERSTAND, PLAYERBLACKJACK, EVAL_ALL, CHECK_FOR_RESTART;

	private BlackJackPlayer player;

	public GameEvent setEventUser(BlackJackPlayer player) {
		this.player = player;
		return this;
	}

	public BlackJackPlayer getEventUser() {
		return player;
	}
}
