package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import com.skilldistillery.blackjack.entities.BlackJackDealer;
import com.skilldistillery.blackjack.entities.BlackJackPlayer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackJackApp implements CLIGame {
	private BlackJackPlayer winner;
	private BlackJackDealer dealer;
	private List<BlackJackPlayer> players;
	private BlackJackUI UI;
	private Stack<GameEvent> eventQueue;

	@Override
	public void pushEvent(GameEvent event) {
		eventQueue.push(event);
	}

	public BlackJackApp() {
		UI = new BlackJackUI(this);
		dealer = new BlackJackDealer();
		players = new ArrayList<>();
		players.add(dealer);
		eventQueue = new Stack<>();
	}

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.run();
	}

	public void run() {
		String userName = UI.getString("Hello, welcome to the black-jack table, what is your name?");
		BlackJackPlayer userPlayer = new BlackJackPlayer(userName);
		players.add(userPlayer);
		eventLoop();
	}

	private List<BlackJackPlayer> playersWithDealerAtEnd() {
		List<BlackJackPlayer> list = players.stream().filter((player) -> !(player instanceof BlackJackDealer))
				.collect(Collectors.toList());
		list.add(dealer);
		return list;
	}

	public void eventLoop() {
		GameEvent nowEvent;
		eventQueue.push(GameEvent.START);
		Iterator<BlackJackPlayer> playerIter = playersWithDealerAtEnd().iterator();
		while (true) {
			if (eventQueue.isEmpty()) {
				eventQueue.push(GameEvent.QUIT);
			}
			nowEvent = eventQueue.pop();
			switch (nowEvent) {
			case START:
				startingDeal();
				eventQueue.push(GameEvent.PLAYERTURN.setEventUser(playerIter.next()));
				break;
			case RESTART:
				resetAll();
				playerIter = playersWithDealerAtEnd().iterator();
				eventQueue.push(GameEvent.START);
				break;
			case PLAYERBLACKJACK:
				UI.printEvent("BlackJack! " + nowEvent.getEventUser().getName() + " has won!");
				UI.pauseForEnter();
				eventQueue.push(GameEvent.CHECK_FOR_RESTART);
				break;
			case PLAYERTURN:
				dealer.shuffle();
				playerTurn(nowEvent.getEventUser());
				break;
			case PLAYERBUST:
				UI.printErr(nowEvent.getEventUser().getName() + " has busted!");
				UI.pauseForEnter();
				eventQueue.push(GameEvent.EVAL_ALL);
				break;
			case PLAYERSTAND:
				if (playerIter.hasNext()) {
					eventQueue.push(GameEvent.PLAYERTURN.setEventUser(playerIter.next()));
				} else {
					eventQueue.push(GameEvent.EVAL_ALL);
				}
				break;
			case EVAL_ALL:
				UI.printEvent(getWinner());
				eventQueue.push(GameEvent.CHECK_FOR_RESTART);
				break;
			case CHECK_FOR_RESTART:
				UI.printInfo("Would you like to play another game?");
				String userChoice = UI.getUserChoice("yes", "no");
				if (userChoice != null && userChoice.equals("yes")) {
					eventQueue.push(GameEvent.RESTART);
				} else {
					eventQueue.push(GameEvent.QUIT);
				}
				break;
			case QUIT:
				UI.printEvent("exiting");
				return;
			default:
				UI.printErr("an unresolved error has occured");
				eventQueue.push(GameEvent.QUIT);
			}
		}

	}

	public String getWinner() {
		BlackJackPlayer winner = null;
		int winHandVal = 0;
		int handVal;
		for (BlackJackPlayer player : playersWithDealerAtEnd()) {
			handVal = player.getHandValue();
			if (winHandVal < handVal && handVal <= 21) {
				winner = player;
				winHandVal = winner.getHandValue();
			}
		}
		if (winner==null) return "nobody wins.";
		return winner.getName() + " is the winner!\n" + winner.toString();
	}

	public String playerChooseHitOrStand(BlackJackPlayer player) {
		if (player instanceof BlackJackDealer) {
			String dealerChoice = ((BlackJackDealer) player).mustHit() ? "hit" : "stand";
			UI.printEvent("Dealer chooses:  " + dealerChoice);
			return dealerChoice;
		} else {
			return UI.getUserChoice("hit", "stand");
		}
	}

	public void playerTurn(BlackJackPlayer player) {
		UI.printStd(player.getName() + "'s turn");
		if (player == dealer && dealer.firstCardIsHidden()) {
			dealer.flipFirstCard();
		}
		displayAllHands();
		evaluateHand(player);
		if (player.isUnder()) {
			String actionChoice = playerChooseHitOrStand(player);
			if (actionChoice == null) {
				return;
			}
			if (actionChoice.equals("hit")) {
				player.addCard(dealer.dealCard());
				eventQueue.push(GameEvent.PLAYERTURN.setEventUser(player));
			} else if (actionChoice.equals("stand")) {
				eventQueue.push(GameEvent.PLAYERSTAND.setEventUser(player));
			}
		}
	}

	public boolean evaluateHand(BlackJackPlayer player) {
		if (player.isBlackJack()) {
			eventQueue.push(GameEvent.PLAYERBLACKJACK.setEventUser(player));
			return false;
		} else if (player.isBust()) {
			eventQueue.push(GameEvent.PLAYERBUST.setEventUser(player));
			return false;
		} else {
			return true;
		}
	}

	public void resetAll() {
		for (BlackJackPlayer player : playersWithDealerAtEnd()) {
			player.clearHand();
		}
		dealer.resetDeck();
	}

	public void displayWinner() {
		if (winner == null) {
			System.out.println("The house wins again!");
		} else {
			System.out.println(winner.getName() + " Wins!");
		}
	}

	public void displayAllHands() {
		UI.printInfo("Hands at the table");
		UI.printErr(dealer.toString());
		UI.displayAll(players.stream().filter((player)-> !(player instanceof BlackJackDealer)).map((player) -> player.toString()).collect(Collectors.toList()));
	}

	public void startingDeal() {
		for (int i = 0; i < 2; i++) {
			for (Player player : playersWithDealerAtEnd()) {
				player.addCard(dealer.dealCard());
			}
		}
		dealer.flipFirstCard();
	}

}
