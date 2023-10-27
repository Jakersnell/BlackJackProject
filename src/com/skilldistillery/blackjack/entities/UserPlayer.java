package com.skilldistillery.blackjack.entities;

public class UserPlayer extends Player {
	private String name;
	
	public UserPlayer(String name) {
		this.name = name;
	}

	@Override
	public boolean choosesHit() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void isOut() {
		super.isOut();
		System.out.println(name + " you have lost.");
	}

}
