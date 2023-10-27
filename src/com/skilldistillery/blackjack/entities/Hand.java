package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void clear() {
		cards.clear();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}

	public abstract int getHandValue();

	@Override
	public String toString() {
		return "Hand | cards: " + cards;

	}

}
