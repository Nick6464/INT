import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    private ArrayList<Card> hand;
    private Location location;
    private int moves;

    /**
     * Moves player in desired direction on the board if no wall is in the way
     * @param direction direction to travel in
     * @param distance  how far to move
     */
    public void move(Direction direction, int distance) {
        if (distance > moves) {
            System.out.println("Can only move " + moves + " spaces this turn");
            return;
        }
        if (!location.move(direction, distance)) {
            System.out.println("You cannot move in that direction");
            return;
        }
    }

    /**
     * Rolls dice to determine number of moves this turn
     */
    public void rollDice() {
        int die1 = (int)(Math.random()*6) + 1;
        int die2 = (int)(Math.random()*6) + 1;
        moves = die1 + die2;
        System.out.printf("%s rolled %.0f and %.0f\n", playerName, die1, die2);
    }

    /**
     * Initialises players card hand
     * @param cards cards held this game
     */
    public void dealHand(List<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Adds leftover cards from deck in case of inequal deal
     * @param card  an extra card for the hand
     */
    public void dealHand(Card card) {
        hand.add(card);
    }
}
