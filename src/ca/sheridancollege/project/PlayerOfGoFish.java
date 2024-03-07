/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Sartaj Singh
 * @author Rehmatpal Kaur
 */
public class PlayerOfGoFish extends Player {
    @Override 
    public void play(){};
    private ArrayList<PlayingCard> hand;

    public PlayerOfGoFish(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public void addToHand(PlayingCard card) {
        hand.add(card);
    }

    public ArrayList<PlayingCard> getHand() {
        return hand;
    }
    
    
}
