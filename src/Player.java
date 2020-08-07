//import Cards.Card;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Player {

    private final String playerName;
    public String initials;
    private Board board;
    private ArrayList<Card> hand;
    private Location location;
    private int moves;
    public ArrayList<Card> seen = new ArrayList<>();
    public ArrayList<Card> unseen = new ArrayList<>();

    //state of the players turn
    private boolean takingTurn = false;

    /**
     * Allows player to perform move and guess actions before ending their turn
     */
    public void takeTurn() {
        rollDice();
        startTurn();
        while (takingTurn) {
            UI.userTurn(this, moves, playerLocation());

            //User Input// -- Temp hardcode to prevent error
            //Direction dir = Direction.NORTH;
//            int dist = 0;
//
//            if (dist > 0)
//                move(dir, dist);
//            else
//                break; //Player has chosen to stop early
        }

//        if (location.inRoom()) {
//            //TODO - allow player to question other player
//            //TODO - allow player to make a guess
//            //TODO - Move into UI class
//        }
    }

    /**
     * Sets player to have started their turn
     */
    public void startTurn() {
        takingTurn = true;
    }

    /**
     * Sets player to have ended their turn
     */
    public void endTurn() {
        takingTurn = false;
    }

    public void playerMoves(){
        try {
            Direction direction;
            String input = UI.userInput("What Direction do you want to move?\nNorth, South, East or West");
            direction = switch (input.toLowerCase()) {
                case "north" -> Direction.NORTH;
                case "south" -> Direction.SOUTH;
                case "east" -> Direction.EAST;
                case "west" -> Direction.WEST;
                default -> throw new IllegalStateException("Unexpected value: " + input);
            };
            String userDistance = UI.userInput("How far do you want to move " + direction.toString() + "?");
            int distance = Integer.parseInt(userDistance);
            while (distance > 0) {
                if (location.move(direction, board)) {
                    moves--;
                    distance--;
                }
                else break;
            }
        } catch (Exception e){
            System.out.println("Please enter a valid move");
        }
    }

    /**
     * Moves player in desired direction on the board if no wall is in the way
     *
     * @param direction direction to travel in
     * @param distance  how far to move
     */
    public int move(Direction direction, int distance) {
        //TODO - I dont think this is initialised fully, may need to be edited in Location as well as here
        //TODO - Add checking to see if a move is valid
        //TODO - make so cant end turn as another player
        try {
            if (distance > moves) {
                throw new IllegalStateException("Unexpected value");
            }
            while (distance > 0) {
                if (!location.move(direction, board)) {
                    System.out.println("You cannot move further in that direction");
                    return distance;
                }
                distance--;
            }
        } catch (IllegalStateException e) {
            System.out.println("Can only move " + moves + " spaces this turn");
        }
        return 0;
    }

    /**
     * Player is being questioned, and must be moved to the room in question
     */
    public void suspect(Card room, Card suspect, Card weapon) {
        //TODO - move the player to room where investigation is being held
    }

    /**
     * Rolls dice to determine number of moves this turn
     */
    public void rollDice() {
        int die1 = (int) (Math.random() * 6) + 1;
        int die2 = (int) (Math.random() * 6) + 1;
        moves = die1 + die2;
        System.out.printf("%s rolled %d and %d\n", playerName, die1, die2);
    }

    /**
     * Initialises players card hand
     *
     * @param cards cards held this game
     */
    public void dealHand(List<Card> cards) {
        hand = new ArrayList<>(cards);
        seen.addAll(cards);
        unseen.removeAll(cards);
        Collections.sort(hand);
    }

    /**
     * Adds leftover cards from deck in case of unequal deal
     *
     * @param card an extra card for the hand
     */
    public void dealHand(Card card) {
        hand.add(card);
        seeCard(card);
    }

    /**
     * Adds card from unseen list to seen list
     * @param card - the card to see
     */
    public void seeCard(Card card) {
        seen.add(card);
        unseen.remove(card);
        Collections.sort(seen);
        Collections.sort(unseen);
    }


    /**
     * Gets the players hand
     * @return - returns an arraylist of cards (players hand)
     */
    public ArrayList<Card> getHand() { return this.hand; }

    /**
     * Gets the cards the player has seen
     * @return - returns an arraylist of cards (player has seen)
     */
    public ArrayList<Card> getSeen() { return this.seen; }

    /**
     * Gets the cards the player has not seen
     * @return - returns an arraylist of cards (player has seen)
     */
    public ArrayList<Card> getUnseen() { return this.unseen; }



    public Card showCard(Room room, Weapon weapon, Character character) {
        //TODO - show a card to another player
        return null;
    }



    /**
     * Creates a new player, and assigns a position based on chosen character
     * @param name the name of the chosen character
     */
    public Player(String name, Board board) {
       this.board = board;
       playerName = name;
        switch (name) {
            case "Miss Scarlett"    -> {location = new Location('Y', 8);
                                        initials = "MS";}
            case "Rev Green"        -> {location = new Location('A', 15);
                                        initials = "RG";}
            case "Colonel Mustard"  -> {location = new Location('R', 1);
                                        initials = "CM";}
            case "Professor Plum"   -> {location = new Location('T', 24);
                                        initials = "PP";}
            case "Mrs. Peacock"     -> {location = new Location('G', 24);
                                        initials = "MP";}
            case "Mrs. White"       -> {location = new Location('A', 10);
                                        initials = "MW";}
        }
    }

    public Location getLocation() { return location; }

    /**
     * Gets the players location and returns if there are walls, and in what direction.
     * @return returns a String describing the players location
     */
    public String playerLocation() {
        Tile currentTile = board.getTile(location.getYIndex(), location.getX());
        StringBuilder sb = new StringBuilder();
        //Player's name and location
        sb.append(playerName).append(" is currently at ").append(location.toString());
        if (currentTile instanceof RoomTile)
            sb.append(", in the ").append(((RoomTile) currentTile).getRoom().getName());
        sb.append(".\n");
        //Walls around player's location
        if (!currentTile.getWalls().isEmpty()) {
            HashSet<Direction> walls = currentTile.getWalls();
            if (walls.size() == 1)
                sb.append("There is a wall to the ");
            else
                sb.append("There are walls to the ");
        sb.append(walls.toString(), 1, walls.toString().length()-1).append(".\n");
        }

        return sb.toString();
//        String wallDirection = "";
//
//
//        if (!currentTile.getWalls().isEmpty()) {
//            wallDirection = "There is a wall to your: \n";
//            String wallList = "";
//            for (Direction d : currentTile.getWalls()) {
//                wallList = wallList + "\n -" + d.toString();
//            }
//            wallDirection = wallDirection + wallList;
//        }
//        sb.append("You are currently at " + location.toString() + "." + "\n" + wallDirection);
//        return sb.toString();
    }
}
