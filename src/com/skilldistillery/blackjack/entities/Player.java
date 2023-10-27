package com.skilldistillery.blackjack.entities;

public abstract class Player {
	private BlackJackHand hand;
	
	public Player() {
		this.hand = new BlackJackHand();
	}
	
	public abstract boolean choosesHit();
	
	public BlackJackHand getHand() {
		return hand;
	}
	
	public void callEvent(Event reason) {
		clearHand();
	}
	public int checkHand() {
		return hand.getHandValue() - 21;
	}
	
	public void addCard(Card card) {
		hand.addCard(card);
	}
	
	
	public void clearHand() {
		hand.clear();
	}
	
}
