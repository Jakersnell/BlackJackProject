package com.skilldistillery.blackjack.entities;


public class BlackJackDealer extends BlackJackPlayer {
	private Deck deck;

	public BlackJackDealer() {
		super("Dealer");
		deck = new Deck();
	}
	
	public boolean firstCardIsHidden() {
		return hand.cardIsHidden(0);
	}

	public void shuffle() {
		deck.shuffle();
	}
	
	public int cardsLeftInDeck() {
		return deck.cardsLeftInDeck();
	}
	
	public void flipFirstCard() {
		hand.flipCardAt(0);
	}

	public void resetDeck() {
		deck = new Deck();
	}

	public Card dealCard() {
		return deck.dealCard();
	}
	
	public boolean mustHit() {
		if (getHandValue() < 17) {
			return true;
		}
		return false;
	}

}
