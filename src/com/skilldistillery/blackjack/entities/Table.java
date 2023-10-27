package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Player> players;
	private Dealer dealer;
	private Player winner;

	public Table(Player mainUser) {
		players.add(mainUser);
	}

	public void resetAll() {
		dealer.resetDeck();
		for (Player player : players) {
			player.clearHand();
		}
	}

	public boolean noUserPlayers() {
		boolean flag = false;
		for (Player player : players) {
			if (player instanceof Player) {
				flag = true;
			}
		}
		return flag;
	}

	public void dealerDealTo(Player player) {
		player.getHand().addCard(dealer.dealCard());
	}

	public boolean checkAll() {
		List<Player> removedPlayers = new ArrayList<>();
		for (Player player : players) {
			int score = player.checkHand();

			if (score == 21) {
				return false;
			} else if (score > 21) {
				removedPlayers.add(player);
			}

		}
		for (Player player : removedPlayers) {
			player.callEvent(Event.BUST);
		}
		return !players.isEmpty();
	}
}
