/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Harleen Kaur
 * @author Navdeep Kaur
 */
public class GoFishGame extends Game {
    private Deck deck;
    private ArrayList<PlayerOfGoFish> players;

    public GoFishGame(String name) {
        super(name);
        deck = new Deck(52);
        players = new ArrayList<>();
    }

    public void addPlayer(PlayerOfGoFish player) {
        players.add(player);
    }

    @Override
    public void play() {
        // Deal cards to players
        for (PlayerOfGoFish player : players) {
            for (int i = 0; i < 5; i++) {
                player.addToHand(deck.dealCard());
            }
        }

        // The game will end if the deck is empty and all players' hands are also empty
        boolean gameOver = false;
        while (!gameOver) {
            for (PlayerOfGoFish player : players) {
                takeTurn(player);
                if (deck.getSize() == 0 && player.getHand().isEmpty()) {
                    gameOver = true;
                    break;
                }
            }
        }

        // Declare winner
        declareWinner();
    }

    private PlayerOfGoFish getOpponent(PlayerOfGoFish currentPlayer) {
        for (PlayerOfGoFish player : players) {
            if (!player.equals(currentPlayer)) {
                return player;
            }
        }
        return null; // If no opponent is found
    }

    private void takeTurn(PlayerOfGoFish player) {
    Scanner scanner = new Scanner(System.in);

    // Choose an opponent
    System.out.println(player.getName() + ", choose an opponent to ask (e.g., 'Player2'):");
    String opponentName = scanner.nextLine();
    PlayerOfGoFish opponent = null;
    for (PlayerOfGoFish p : players) {
        if (p.getName().equalsIgnoreCase(opponentName)) {
            opponent = p;
            break;
        }
    }
    if (opponent == null) {
        System.out.println("Invalid opponent name. Please try again.");
        return; // Skip turn if invalid opponent is chosen
    }

    // Ask for a card
    System.out.println(player.getName() + ", ask your opponent for a card (e.g., 'Do you have any Aces?'):");
    String input = scanner.nextLine();
    String[] parts = input.split("\\s+");
    String askedRank = parts[parts.length - 1];

    // Check if opponent has the requested card
    boolean hasRequestedCard = false;
    ArrayList<PlayingCard> cardsToRemove = new ArrayList<>();
    for (PlayingCard card : opponent.getHand()) {
        if (card.getRank().equalsIgnoreCase(askedRank)) {
            player.addToHand(card);
            cardsToRemove.add(card);
            hasRequestedCard = true;
            break; // Exit loop once card is found
        }
    }
    opponent.getHand().removeAll(cardsToRemove);

    // Draw from deck if necessary
    if (!hasRequestedCard && deck.getSize() > 0) {
        System.out.println("Go Fish! You draw a card from the deck.");
        player.addToHand(deck.dealCard());
    } else if (hasRequestedCard) {
        System.out.println("You received the card from your opponent.");
    }

    // Check for sets
    checkForSets(player);
}



    private void checkForSets(PlayerOfGoFish player) {
        ArrayList<PlayingCard> hand = player.getHand();
        ArrayList<PlayingCard> setsToRemove = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            PlayingCard card1 = hand.get(i);
            int count = 1;
            for (int j = i + 1; j < hand.size(); j++) {
                PlayingCard card2 = hand.get(j);
                if (card1.getRank().equals(card2.getRank())) {
                    count++;
                }
            }
            if (count == 4) {
                // Add cards to setsToRemove
                setsToRemove.add(card1);
            }
        }

        // Remove completed sets from hand
        hand.removeAll(setsToRemove);
    }

    private int countSets(PlayerOfGoFish player) {
        // Count sets in player's hand
        ArrayList<PlayingCard> hand = player.getHand();
        int numSets = 0;
        for (PlayingCard card : hand) {
            int count = 0;
            for (PlayingCard otherCard : hand) {
                if (card != otherCard && card.getRank().equals(otherCard.getRank())) {
                    count++;
                }
            }
            if (count == 3) {
                numSets++;
            }
        }
        return numSets;
    }

    @Override
    public void declareWinner() {
        // Count sets for each player
        int maximumSets = 0;
        PlayerOfGoFish winner = null;
        for (PlayerOfGoFish player : players) {
            int numOfSets = countSets(player);
            if (numOfSets > maximumSets) {
                maximumSets = numOfSets;
                winner = player;
            }
        }

        // Declare winner
        if (maximumSets > 0) {
            System.out.println("Winner: " + winner.getName() + " has won the game with  " + maximumSets + " sets!");
        } else {
            System.out.println("Oops! No winner found.");
        }
    }
}