package com.skilldistillery.blackjack.menu;


import java.util.Scanner;
import java.util.function.Function;

public class UserMenu {
	protected Scanner scanner;
	private String headerMessage;
	private String onInvalidMessage;
	private String onSuccessMessage;

	public UserMenu() {
		scanner = new Scanner(System.in);
	}

	public UserMenu(String headerMessage, String onInvalidMessage, String onSuccessMessage) {
		this(headerMessage, onInvalidMessage);
		this.onSuccessMessage = onSuccessMessage;
	}

	public UserMenu(String headerMessage, String onInvalidMessage) {
		this(headerMessage);
		this.onInvalidMessage = onInvalidMessage;
	}

	public UserMenu(String headerMessage) {
		this();
		this.headerMessage = headerMessage;
	}

	private void printQuitInstruction() {
		System.out.println("Please enter 0 or 'quit' to quit.");
	}

	private void enterToContinue() {
		System.out.println("Please press enter to continue.");
		scanner.nextLine();
	}

	private boolean isQuit(String input) {
		return input.equals("0") || input.toLowerCase().equals("quit");
	}

	private boolean binaryValidation(String input, String str1, String str2) {
		input = input.toLowerCase();
		return (input.equals(str1) || input.equals(str2));
	}

	public String binaryValidationLoop(String str1, String str2) {
		return parseLoop("Please enter 'yes' or 'no'.", (str) -> str, (str) -> binaryValidation(str, str1, str2));
	}

	public int parseValidIntLoop(String message, int min, int max) {
		return parseLoop(message, Integer::parseInt, (num) -> (num >= min && num <= min));
	}

	public void afterOutput() {
		enterToContinue();
		for (int i = 0; i < 1000; i++) {
			System.out.println();
		}
	}

	public <T> T parseLoop(String message, Function<String, T> parseMethod, Function<T, Boolean> isValidChecker)
			throws IllegalArgumentException {
		while (true) {
			try {
				System.out.println(message);
				printQuitInstruction();
				String input = scanner.nextLine();
				if (isQuit(input)) {
					return null;
				}
				T parsed = parseMethod.apply(input);
				if (isValidChecker.apply(parsed)) {
					return parsed;
				} else {
					throw new IllegalArgumentException("Could not parse from input: " + input);
				}
			} catch (IllegalArgumentException e) {
				System.out.println(onInvalidMessage);
				afterOutput();
			}
		}
	}

	public String getHeaderMessage() {
		return headerMessage;
	}

	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}

	public String getOnInvalidMessage() {
		return onInvalidMessage;
	}

	public void setOnInvalidMessage(String onInvalidMessage) {
		this.onInvalidMessage = onInvalidMessage;
	}

	public String getOnSuccessMessage() {
		return onSuccessMessage;
	}

	public void setOnSuccessMessage(String onSuccessMessage) {
		this.onSuccessMessage = onSuccessMessage;
	}

}
