package com.skilldistillery.blackjack.entities;

public class BlackJackPlayer extends Player {
	
	public BlackJackPlayer(String name) {
		super(new BlackJackHand(), name);
	}
	
	private BlackJackHand getHand() {
		return (BlackJackHand)hand;
	}
	
	@Override
	public void addCard(Card card) {
		super.addCard(card);
		getHand().setFirstAceToLow();
	}
	
	public int getHandValue() {
		return getHand().getValue();
	}
	
	public boolean isBust() {
		return getHand().isBust();
	}
	
	public boolean isBlackJack() {
		return getHand().isBlackJack();
	}
	
	public boolean isUnder() {
		return getHand().isUnder();
	}

}
