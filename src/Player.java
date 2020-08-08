//import Cards.Card;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Player implements Comparable {

    public final String playerName;
    public int playOrder;
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
            UI.userTurn(this);
        }
    }

    public int getMoves() { return moves; }

    /**
     * Gets the cards the player has seen
     * @return - returns an arraylist of cards (player has seen)
     */
    public ArrayList<Card> getSeen() {
        Collections.sort(this.seen);
        return this.seen;
    }


    /**
     * Gets the cards the player has not seen
     * @return - returns an arraylist of cards (player has seen)
     */
    public ArrayList<Card> getUnseen() {
        Collections.sort(this.unseen);
        return this.unseen;
    }

    /**
     * Sets player to have started their turn
     */
    public void startTurn() { takingTurn = true; }

    /**
     * Sets player to have ended their turn
     */
    public void endTurn() { takingTurn = false; }

    /**
     * How the player wants to move
     */
    public void playerMoves(){
        //TODO - needs to check distance <= moves
        //TODO - notify of wall collision
        board.getTile(location.getYIndex(),location.getX()).setVacant();
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
            int distance = Math.min(moves,Integer.parseInt(userDistance));
            while (distance > 0) {
                if (location.move(direction, board)) {
                    moves--;
                    distance--;
                }
                else {
                    System.out.println("Your path is obstructed.");
                    break;
                }
            }
        } catch (Exception e){
            System.out.println("Please enter a valid move");
        }
        board.getTile(location.getYIndex(),location.getX()).setOccupied(this);
    }

    /**
     * Used for testing to force the moves a player gets
     * @param moves - number of moves the player gets
     */
    public void setMoves(int moves){
        this.moves = moves;
    }

    /**
     * Moves player in desired direction on the board if no wall is in the way
     *
     * @param direction direction to travel in
     * @param distance  how far to move
     */
    public int move(Direction direction, int distance) {
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
     * Rolls dice to determine number of moves this turn
     */
    public void rollDice() {
        moves = (int) (Math.random() * 6) + 1;
        System.out.printf("%s rolled a %d.\n", playerName, moves);
    }

    /**
     * Checks if a player has any of the selected cards
     * @param weapon - card to check
     * @param suspect - card to check
     * @param room - card to check
     * @return - the card they have selected to show
     */
    public Card hasCard(Card weapon, Card suspect, Card room){
        ArrayList<Card> has = new ArrayList<>();
        for(Card card : hand){
            if (card.toString().equals(weapon.toString()) ||
                card.toString().equals(suspect.toString()) ||
                card.toString().equals(room.toString())){

                has.add(card);
            }
        }
        if (has.isEmpty()) {
            return null;
        } else {
            return UI.cardSelect(has, this);
        }
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
        seen.add(card);
        unseen.remove(card);
    }

    /**
     * Gets a players hand
     * @return - their hand
     */
    public ArrayList<Card> getHand() {
        return this.hand;
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
                                        initials = "MS";
                                        playOrder = 1;}
            case "Rev Green"        -> {location = new Location('A', 15);
                                        initials = "RG";
                                        playOrder = 4;}
            case "Colonel Mustard"  -> {location = new Location('R', 1);
                                        initials = "CM";
                                        playOrder = 2;}
            case "Professor Plum"   -> {location = new Location('T', 24);
                                        initials = "PP";
                                        playOrder = 6;}
            case "Mrs. Peacock"     -> {location = new Location('G', 24);
                                        initials = "MP";
                                        playOrder = 5;}
            case "Mrs. White"       -> {location = new Location('A', 10);
                                        initials = "MW";
                                        playOrder = 3;}
        }
        board.getTile(location.getYIndex(), location.getX()).setOccupied(this);
    }

    /**
     * Gets a players location
     * @return - Location
     */
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
        if (currentTile instanceof RoomTile) {
            RoomTile rt = (RoomTile) currentTile;
            sb.append(", in the ").append(rt.getRoom().getName());
            sb.append(".\n");
            if (!rt.getRoom().getPotentialWeapons().isEmpty()) {
                sb.append("Looking for potential weapons you see: ");
                for(Weapon wep : rt.getRoom().getPotentialWeapons())
                    sb.append(wep.getName()).append(", ");
                sb.replace(sb.length()-3,sb.length()-1,".\n");
            }
        }
        //Walls around player's location
        else if (!currentTile.getWalls().isEmpty()) {
            TreeSet<Direction> walls = currentTile.getWalls();
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Player))
            return -1;
        return playOrder - ((Player) o).playOrder;
    }
}
