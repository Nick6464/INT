import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    private ArrayList<Card> hand;
    private Location location;
    private int moves;

    /**
     * Allows player to perform move and guess actions before ending their turn
     */
    public void takeTurn() {
        rollDice();
        while (moves > 0) {
            System.out.println(moves + " moves remaining");
            //TODO - Ask user for direction and distance to move

            //User Input// -- Temp hardcode to prevent error
            Direction dir = Direction.NORTH;
            int dist = 0;

            if (dist > 0)
                move(dir,dist);
            else
                break; //Player has chosen to stop early
        }

        if (location.inRoom()) {
            //TODO - allow player to make a guess
        }
    }

    /**
     * Moves player in desired direction on the board if no wall is in the way
     * @param direction direction to travel in
     * @param distance  how far to move
     */
    public int move(Direction direction, int distance) {
        if (distance > moves) {
            System.out.println("Can only move " + moves + " spaces this turn");
            return distance;
        }
        while (distance > 0) {
            if (!location.move(direction)) {
                System.out.println("You cannot move further in that direction");
                return distance;
            }
            distance--;
        }
        return 0;
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
        hand = new ArrayList<>(cards);
    }

    /**
     * Adds leftover cards from deck in case of unequal deal
     * @param card  an extra card for the hand
     */
    public void dealHand(Card card) {
        hand.add(card);
    }

    /**
     * Creates a new player, and assigns a position based on chosen character
     * @param name  the name of the chosen character
     */
    public Player(String name) {
        switch (name) {
            case "Miss Scarlett":
                location = new Location('Y', 8);
            case "Rev Green":
                location = new Location('A', 15);
            case "Colonel Mustard":
                location = new Location('R', 1);
            case "Professor Plum":
                location = new Location('T', 24);
            case "Mrs. Peacock":
                location = new Location('G', 24);
            case "Mrs. White":
                location = new Location('A', 10);
        }
    }
}
