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
public class GoFishGame extends Game{
        private Deck deck;
        private ArrayList<PlayerOfGoFish> players;
    //adding players list to the game
    public GoFishGame(String name) {
        super(name);
        deck = new Deck(52);
        players = new ArrayList<>();
    }
    //adding player to players list
    public void addPlayer(PlayerOfGoFish player) {
        players.add(player);
    }
    //giving 4 cards to each player    
    @Override
    public void play() {
    // Deal cards to players
    for (PlayerOfGoFish player : players) {
        for (int i = 0; i < 5; i++) {
            player.addToHand(deck.dealCard());
        }
    }

    // The game will end if the deck is empty and player's hand is also empty
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
    //returning the opponent
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

    // Ask opponent for a card
    System.out.println(player.getName() + ", ask your opponent for a card (e.g., 'Do you have any Aces?'):");
    String input = scanner.nextLine();


    String[] parts = input.split("\\s+");
    String askedRank = parts[parts.length - 1]; 

    // Checking whether opponent has the requested card or not
    PlayerOfGoFish opponent = getOpponent(player);
    boolean hasRequestedCard = false;
    for (PlayingCard card : opponent.getHand()) {
        if (card.getRank().equalsIgnoreCase(askedRank)) {
            player.addToHand(card);
            opponent.getHand().remove(card);
            hasRequestedCard = true;
            break;
        }
    }

    // If opponent doesn't have the card, player draws from deck
    if (!hasRequestedCard && deck.getSize() > 0) {
        System.out.println("Go Fish! You draw a card from the deck.");
        player.addToHand(deck.dealCard());
    } else {
        System.out.println("You received the card from your opponent.");
    }

    // Checking for sets and then removing them from hand
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
            System.out.println("OOPs No winner found");
        }
    }
}

    
