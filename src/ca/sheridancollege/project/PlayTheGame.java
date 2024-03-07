/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Navdeep Kaur
 */
public class PlayTheGame {
    public static void main(String[] args){
        GoFishGame game = new GoFishGame("Go Fish");
        PlayerOfGoFish player1 = new PlayerOfGoFish("Player1");
        PlayerOfGoFish player2 = new PlayerOfGoFish("Player2");
        PlayerOfGoFish player3 = new PlayerOfGoFish("Player3");
        PlayerOfGoFish player4 = new PlayerOfGoFish("Player4");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        
        game.play();
    }
}
