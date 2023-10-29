package com.skilldistillery.blackjack.app;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class BlackJackUI {
	private Scanner scanner;
	private String onInvalidMessage = "Invalid entry.";
	private CLIGame game;

	public BlackJackUI() {
		scanner = new Scanner(System.in);
	}

	public BlackJackUI(CLIGame game) {
		this();
		this.game = game;
	}
	
	public void printStd(String message) {
		System.out.println(message);
	}

	public void printErr(String message) {
		System.out.println("\033[31m" + message + "\033[0m\n");
	}

	public void printEvent(String message) {
		System.out.println("\033[32m" + message + "\033[0m\n");
	}

	public void printInfo(String message) {
		System.out.println("\033[36m" + message + "\033[0m\n");
	}

	public String getString(String message) {
		printInfo(message);
		String val = scanner.nextLine();
		System.out.println();
		return val;
	}

	public void displayAll(List<String> strings) {
		for (String string : strings) {
			printEvent(string);
		}
		pauseForEnter();
	}

	private void printQuitInstruction() {
		printInfo("Please enter 0 or 'quit' to quit.");
	}

	private void enterToContinue() {
		printInfo("Please press enter to continue.");
		scanner.nextLine();
	}

	private boolean checkQuit(String input) {
		boolean isQuit = input.equals("0") || input.toLowerCase().equals("quit");
		if (isQuit) {
			game.pushEvent(GameEvent.QUIT);
		}
		return isQuit;
	}

	public String getUserChoice(String str1, String str2) {
		return getUserInput("Please enter '" + str1 + "' or '" + str2 + "'.", (str) -> str,
				(str) -> (str.equals(str1) || str.equals(str2)));
	}

	public void pauseForEnter() {
		enterToContinue();
	}

	public <T> T getUserInput(String message, Function<String, T> parseMethod, Function<T, Boolean> isValidChecker)
			throws IllegalArgumentException {
		while (true) {
			try {
				printInfo(message);
				printQuitInstruction();
				String input = scanner.nextLine();
				if (checkQuit(input)) {
					game.pushEvent(GameEvent.QUIT);
					return null;
				}
				T parsed = parseMethod.apply(input);
				if (isValidChecker.apply(parsed)) {
					return parsed;
				} else {
					throw new IllegalArgumentException("Could not parse from input: " + input);
				}
			} catch (IllegalArgumentException e) {
				printErr(onInvalidMessage);
				pauseForEnter();
			}
		}
	}

	public String getOnInvalidMessage() {
		return onInvalidMessage;
	}

	public void setOnInvalidMessage(String onInvalidMessage) {
		this.onInvalidMessage = onInvalidMessage;
	}

}
