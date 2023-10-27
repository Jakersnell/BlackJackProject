package com.skilldistillery.blackjack.entities;


public enum Rank {
	TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
	TEN(10, "10"), JACK(10, "J"), QUEEN(12, "Q"), KING(10, "K"), ACE(11, "A");

	private int value;
	private String symbol;

	private Rank(int value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public String getSymbol() {
		return symbol; 
	}
	
}
