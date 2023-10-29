package com.skilldistillery.blackjack.entities;

public abstract class Player {
	protected Hand hand;
	private String name;

	public Player() {
	}

	public Player(String name) {
		this();
		this.name = name;
	}

	public Player(Hand hand, String name) {
		this(name);
		this.hand = hand;
	}

	public int getHandValue() {
		return hand.getValue();
	}

	public String viewHand() {
		return name + " " + hand.toString();
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public void removeCardAt(int index) {
		hand.removeCardAt(index);
	}

	public void clearHand() {
		hand.clear();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player " + name + ":\n" + hand.toString();
	}

}
