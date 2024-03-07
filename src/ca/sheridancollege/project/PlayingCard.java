/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Harleen Kaur
 */
public class PlayingCard extends Card{
    private String rank;

    public PlayingCard(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    private String suit;
    @Override
    public String toString(){
        return "Playing Go Fish";
    };
}
