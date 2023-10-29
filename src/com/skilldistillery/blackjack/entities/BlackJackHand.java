package com.skilldistillery.blackjack.entities;

public class BlackJackHand extends Hand {

	public BlackJackHand() {

	}

	@Override
	public int getValue() {
		return cards.stream().mapToInt(Card::getValue).sum();
	}
	
	public void setFirstAceToLow() {
		if (isBust()) {
			for (Card card : cards) {
				if (card.ifAceSetToLow()) {
					return;
				}
			}
		}
	}

	public boolean isBlackJack() {
		return getValue() == 21;
	}

	public boolean isBust() {
		return getValue() > 21;
	}
	
	public boolean isUnder() {
		return getValue() < 21;
	}

}
