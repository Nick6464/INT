import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String playerName;
    private ArrayList<Card> hand;
    private Location location;
    private int moves;

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

    public void rollDice() {
        int die1 = (int)(Math.random()*6) + 1;
        int die2 = (int)(Math.random()*6) + 1;
        moves = die1 + die2;
        System.out.printf("%s rolled %.0f and %.0f\n", playerName, die1, die2);
    }

    public void dealHand(List<Card> cards) {
        hand.addAll(cards);
    }

    public void dealHand(Card card) {
        hand.add(card);
    }
}
