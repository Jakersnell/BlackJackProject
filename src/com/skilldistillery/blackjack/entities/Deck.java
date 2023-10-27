package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;

	public Deck() {
		genDeck();
		shuffle();
	}
	
	private void genDeck() {
		cards = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card newCard = new Card(rank, suit);
				cards.add(newCard);
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public int cardsLeftInDeck() {
		return cards.size();
	}

	public Card dealCard() {
		Card returnCard = null;
		if (!cards.isEmpty()) {
			returnCard = cards.remove(0);
		}
		return returnCard;
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
}
