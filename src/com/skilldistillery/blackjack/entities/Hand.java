package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public abstract int getValue();

	public Card removeCardAt(int index) {
		return cards.remove(index);
	}
	
	public boolean cardIsHidden(int index) {
		return cards.get(index).isFaceDown();
	}

	public void flipCardAt(int index) {
		cards.get(index).flipCard();
	}

	public void clear() {
		cards.clear();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("Hand | cards: [ ");
		for (int i = 0; i < cards.size(); i++) {
			string.append(String.format("(#%d) %s, ", i+1, cards.get(i).toString()));
		}
		if (!cards.isEmpty()) {
			string.deleteCharAt(string.length() - 2);
		}
		string.append("] total: ");
		string.append(getValue());

		return string.toString();

	}

}
