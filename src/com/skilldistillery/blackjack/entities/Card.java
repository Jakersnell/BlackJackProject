package com.skilldistillery.blackjack.entities;

import java.util.Objects;

public class Card {
	private Rank rank;
	private Suit suit;
	private boolean isFaceDown;

	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		isFaceDown = false;
	}
	
	public boolean isFaceDown() {
		return isFaceDown;
	}
	
	public void flipCard() {
		isFaceDown = !isFaceDown;
	}
	
	public boolean ifAceSetToLow() {
		if (rank == Rank.ACE) {
			rank.setValue(1);
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isFaceDown, rank, suit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return isFaceDown == other.isFaceDown && rank == other.rank && suit == other.suit;
	}

	public int getValue() {
		if (isFaceDown) return 0;
		return rank.getValue();
	}
	
	
	@Override
	public String toString() {
		if (isFaceDown) {
			return "##hidden##";
		}
		return rank.getSymbol() + suit.getSymbol() + " value: " + getValue();
	}
}
