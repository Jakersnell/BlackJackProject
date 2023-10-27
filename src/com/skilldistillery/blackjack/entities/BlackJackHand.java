package com.skilldistillery.blackjack.entities;

public class BlackJackHand extends Hand {

	public BlackJackHand() {

	}

	@Override
	public int getHandValue() {
		return cards.stream().mapToInt(Card::getValue).sum();
	}

	public boolean isBlackJack() {
		return getHandValue() == 21;
	}

	public boolean isBust() {
		return getHandValue() > 21;
	}

}
