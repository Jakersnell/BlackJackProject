package com.skilldistillery.blackjack.entities;

import java.util.List;

public class Dealer extends Player {
	private Deck deck;

	public Dealer() {
		resetDeck();
	}

	public void shuffle() {
		deck.shuffle();
	}

	public void resetDeck() {
		deck = new Deck();
	}

	public Card dealCard() {
		return deck.dealCard();
	}

	public void dealDealerInit() {
		Card flippedCard = dealCard();
		flippedCard.flipCard();
		getHand().addCard(flippedCard);
		getHand().addCard(dealCard());
	}

	public void startingDeal(List<Player> players) {
		dealDealerInit();
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				player.getHand().addCard(dealCard());
			}
		}
	}

	@Override
	public boolean choosesHit() {
		if (checkHand() < 17) {
			return true;
		}
		return false;
	}

}
