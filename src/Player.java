//import Cards.Card;

import Cards.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {
    private final String playerName;
    private Board board;
    private ArrayList<Card> hand;
    private Location location;
    private int moves;

    /**
     * Allows player to perform move and guess actions before ending their turn
     */
    public void takeTurn() {

        rollDice();
        while (moves > 0) {
            String userMove = UI.userTurn(moves, playerLocation());
            //TODO - Ask user for direction and distance to move; or could implement key listener (key listener could be initialised in Game, and passed to takeTurn as a parameter
            //TODO - undo option?
            switch (userMove) {
                case "/help":
                    help();
                    break;
                case "move":
                    playerMoves();
                    break;
                case "suspect":
                    playerSuspects();
                    break;
            }
            //User Input// -- Temp hardcode to prevent error
            Direction dir = Direction.NORTH;
            int dist = 0;

            if (dist > 0)
                move(dir, dist);
            else
                break; //Player has chosen to stop early
        }

//        if (location.inRoom()) {
//            //TODO - allow player to question other player
//            //TODO - allow player to make a guess
//        }
    }

    public void playerMoves(){
        try {
            String direction = UI.userInput("What Direction do you want to move?");
        } catch (Exception e){

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
//        if (distance > moves) {
//            System.out.println("Can only move " + moves + " spaces this turn");
//            return distance;
//        }
        while (distance > 0) {
            if (!location.move(direction, board)) {
                System.out.println("You cannot move further in that direction");
                return distance;
            }
            distance--;
        }
        return 0;
    }

    /**
     * Player is being questioned, and must be moved to the room in question
     */
    public void suspect(Room investigation) {
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
    }

    /**
     * Adds leftover cards from deck in case of unequal deal
     *
     * @param card an extra card for the hand
     */
    public void dealHand(Card card) {
        hand.add(card);
    }

    public void showHand() {
        //TODO - show the players hand (do we also want to store cards shown to player, or make them remember manually?
    }

    public Card showCard() {
        //TODO - show a card to another player
        return null;
    }

    /**
     * Creates a new player, and assigns a position based on chosen character
     *
     * @param name the name of the chosen character
     */
    public Player(String name, Board board) {
       this.board = board;
       playerName = name;
        switch (name) {
            case "Miss Scarlett":
                location = new Location('Y', 8);
                break;
            case "Rev Green":
                location = new Location('A', 15);
                break;
            case "Colonel Mustard":
                location = new Location('R', 1);
                break;
            case "Professor Plum":
                location = new Location('T', 24);
                break;
            case "Mrs. Peacock":
                location = new Location('G', 24);
                break;
            case "Mrs. White":
                location = new Location('A', 10);
                break;
        }
    }

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
        sb.append(walls.toString().substring(1,walls.toString().length()-1)).append(".\n");
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
