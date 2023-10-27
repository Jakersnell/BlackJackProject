package com.skilldistillery.blackjack.entities;


public enum Suit {
	HEARTS("hearts", "❤"), SPADES("spades", "♠"), CLUBS("clubs", "♣"), DIAMONDS("diamonds", "♦");
	
	private String name;
	private String symbol;
	
	private Suit(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getSymbol() {		
		return symbol;
	}
}
