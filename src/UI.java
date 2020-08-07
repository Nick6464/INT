import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UI {
    private static Board board;
    public ArrayList<Card> allWeapons = new ArrayList<>();

    //TODO - Questions at the beginning of each turn, where do you want to move?
    //TODO - describeTile(), when you move a tile, describe what is around you
    //TODO - describeRoom(), when you enter a room, describe room name, what weapons and players are already in the room //PlayerLoction already describes all but other players
    //TODO - ask for who you suspect(CONDITION: if player are in a room)
    //TODO - declareAccusation() - the final declaration of who it is with what wep in what room*

    /**
     * A String for a line that's sole purpose its to make things pretty
     */
    public static String separator =
            "****************************************************************************";

    /**
     * A string for displaying the help commands
     */
    public static String helpDef =
            "HELP COMMANDS:" +
                    "\n     /gt - General Tile definitions" +
                    "\n     /st - Spawn Tile definitions" +
                    "\n     /rt - Room Tile definitions" +
                    "\n     /exit - Exits the help menu \n";
    /**
     * A string for of the definition of the general Tiles
     */
    public static String generalDef =
            "GENERAL TILES:" +
                    "\n     //       - Inaccessible Tiles" +
                    "\n     01 to 24 - X coordinate" +
                    "\n     A to V   - Y coordinate" +
                    "\n     __       - Corridor Tile" +
                    "\n     []       - Door Tile \n";

    /**
     * A string of the definition of the player spawn Tiles
     */
    public static String spawnDef =
            "SPAWN TILES:" +
                    "\n     mw  - Mrs. White" +
                    "\n     mg  - Mr. Green" +
                    "\n     mp  - Mrs. Peacock" +
                    "\n     pp  - Prof. Plum" +
                    "\n     ms  - Miss Scarlett" +
                    "\n     cm  - Col. Mustard \n";

    /**
     * A string printing the room tile definitions
     */
    public static String roomDef =
            "ROOM TILES:" +
                    "\n     KI  - Kitchen" +
                    "\n     BA  - Ball Room" +
                    "\n     CO  - Conservatory" +
                    "\n     DI  - Dining Room" +
                    "\n     BI  - Billiard Room" +
                    "\n     LI  - Library" +
                    "\n     LO  - Lounge" +
                    "\n     HA  - Hall" +
                    "\n     ST  - Study" +
                    "\n     CC  - Cellar(Inaccessible) \n";

    /**
     * A string printing who the
     */
    public static String actionsDef =
            "ACTIONS:" +
                    "\n     /help     - Brings up the help menu." +
                    "\n     move      - Begins your move action." +
                    "\n     hand      - Displays the cards in your hand." +
                    "\n     look      - Displays the area around your character." +
                    "\n     map       - Displays a map of the entire board." +
                    "\n     suggest   - Begins the suggestion action (Can only perform this if you are in a room)." +
                    "\n     accuse    - Begins the accusation action (If you are successful you will win the game, if not you are out!" +
                    "\n     end       - Ends your turn.\n";

    /**
     * prints the map
     */
    public static void displayMap() {
        System.out.println(separator);
        System.out.println(board.printBoard());
        System.out.println(separator);
    }


    /**
     * gets they hand(cards player holds) of the player and prints it
     *
     * @param p - the player who's hand is getting accessed
     */
    public static void displayHand(Player p) {
        System.out.println(separator);
        System.out.println("Your hand contains...");
        displayList(p.getHand());
        System.out.println(separator);
        //System.out.println(Arrays.toString(p.getHand().toArray()));
    }

    /**
     * prints out list of cards the player has seen
     * @param p - player
     */
    public static void displaySeen(Player p) {
        System.out.println(separator);
        System.out.println("The cards you have seen...");
        displayList(p.getSeen());
        System.out.println(separator);
    }

    /**
     * prints out list of cards player has not seen
     * @param p - player
     */
    public static void displayUnseen(Player p) {
        System.out.println(separator);
        System.out.println("Your Unseen cards are...");
        displayList(p.getUnseen());
        System.out.println(separator);
    }

    /**
     * prints each item in a list on a new line (makes easier to read)
     * @param list - list to print
     */
    public static void displayList(ArrayList<Card> list) {
        for (Card c : list) {
            System.out.println("\t-" + c.toString());
        }
        System.out.println(separator);
    }


    /**
     * Help
     * Reads the command entered and prints the appropriate help menu
     */
    public static void help() {
        System.out.println(separator);
        System.out.println(helpDef);
        String command = userInput("Enter a command: ");

        switch (command) {
            //General Tiles
            case "/gt" -> {
                System.out.println(separator);
                System.out.println(generalDef);
            }
            //Spawn Tiles
            case "/st" -> {
                System.out.println(separator);
                System.out.println(spawnDef);
            }
            //Room Tiles
            case "/rt" -> {
                System.out.println(separator);
                System.out.println(roomDef);
            }
            //Exit Command
            case "/exit" -> System.out.println("Exiting help menu...\n");
            //Invalid input scenario
            default -> System.out.println("Invalid Command");
        }
    }

    /**
     * Prints 50 lines of "*" in order to push the old screen out of view.
     * this will help implement the privacy of you cards.
     */
    public static void clearScreen() {
        System.out.println(separator);
        for (int i = 0; i < 50; i++) {
            System.out.println("*");
        }
        System.out.println(separator);
        System.out.println("Screen cleared...\n");
    }

    /**
     * method for the players turn
     *
     * @param p              - the current player
     * @param moves          - how many moves the player has left
     * @param playerLocation - the players location on the board
     */
    public static void userTurn(Player p, int moves, String playerLocation) {
        System.out.println(playerLocation);
        System.out.println(moves + " moves remaining");
        System.out.println("What actions would you like to perform? (typing 'actions' will display commands)");
        Scanner sc = new Scanner(System.in);

        //TODO - undo option?

        switch (sc.nextLine().toLowerCase()) {
            case "actions":
                System.out.println(actionsDef);
                break;
            case "/help":
                help();
                break;
            case "move":
                p.playerMoves();
                break;
            case "hand":
                displayHand(p);
                break;
            case "seen":
                displaySeen(p);
                break;
            case "unseen":
                displayUnseen(p);
                break;
            case "suggest":
                if (board.getTile(p.getLocation().getYIndex(), p.getLocation().getX()) instanceof RoomTile) {
                    Card suspect = playerSuspect(true);
                    Card weapon = weaponSuspect();
                    Card room = roomSuspect(p);
                    Game.suspect(room, suspect,weapon, p);
                } else {
                    System.out.println("You aren't currently in a room.");
                }
                break;
            case "map":
                System.out.println(separator);
                displayMap();
                System.out.println(separator);
                break;
            case "look":
                System.out.println(separator);
                playerArea(p);
                System.out.println(separator);
                break;
            case "accuse":
                //TODO - the accuse action;
                Card suspect = playerSuspect(false);
                Card weapon = weaponSuspect();
                Card room = roomSuspect(p);
                Game.running = Game.accuse(suspect, weapon, room, p);
                break;
            case "end":
                p.endTurn();
        }
    }

    public static Card cardSelect(ArrayList<Card> has, Player player){
        for(int i = 0; i < has.size(); i++){
            System.out.println((i + 1) + " : " + has.get(i).toString());
        }
        String selected = userInput(player.playerName + " which card do you wish to show?");
        for (Card card : has) {
            if (selected.toString().equals(card.toString())) {
                return card;
            }
        }
        try{
            if (Integer.parseInt(selected) >= 1 &&
                    Integer.parseInt(selected) <= has.size()) {
                return has.get(Integer.parseInt(selected) - 1);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid selection");
            return cardSelect(has, player);
        }
    }

    public static Card roomSuspect(Player player) {
        RoomTile current = (RoomTile) board.getTile(player.getLocation().getYIndex(), player.getLocation().getX());
        Room currentRoom = current.getRoom();
        ArrayList<Card> allRooms = new ArrayList<>(Arrays.asList(
                new RoomCard("Kitchen"),
                new RoomCard("Ballroom"),
                new RoomCard("Conservatory"),
                new RoomCard("Dining Room"),
                new RoomCard("Billiard Room"),
                new RoomCard("Library"),
                new RoomCard("Lounge"),
                new RoomCard("Hall"),
                new RoomCard("Study")
        ));
        for (Card room : allRooms) {
            if (("[Room: " + currentRoom.getName() + "]").equals(room.toString())) {
                return room;
            }
        }
        return null;
    }

    public static Card playerSuspect(boolean suggest) {
        String characterSuspect = null;
        ArrayList<Card> allCharacters = new ArrayList<>(Arrays.asList(
                new CharacterCard("Miss Scarlett"),
                new CharacterCard("Rev Green"),
                new CharacterCard("Colonel Mustard"),
                new CharacterCard("Professor Plum"),
                new CharacterCard("Mrs. Peacock"),
                new CharacterCard("Mrs. White")
        ));
        for (int i = 0; i < allCharacters.size(); i++) {
            System.out.println((i + 1) + ": " + allCharacters.get(i));
        }
        if (suggest){
            characterSuspect = userInput("Who are you suspecting?");
        } else {
            characterSuspect = userInput("Who are you accusing?");
        }
        if (allCharacters.contains(new CharacterCard(characterSuspect))) {
            for (int j = 0; j < allCharacters.size(); j++) {
                if (allCharacters.toString().equals(characterSuspect)) {
                    return allCharacters.get(j);
                }
            }
        }
        try {
            if (Integer.parseInt(characterSuspect) >= 1 &&
                    Integer.parseInt(characterSuspect) <= allCharacters.size()) {
                return allCharacters.get(Integer.parseInt(characterSuspect) - 1);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Character does not exist.");
            return playerSuspect(suggest);
        }
    }

    public static Card weaponSuspect() {
        ArrayList<Card> allWeapons = new ArrayList<>(Arrays.asList(
                new WeaponCard("Candlestick"),
                new WeaponCard("Dagger"),
                new WeaponCard("Lead Pipe"),
                new WeaponCard("Revolver"),
                new WeaponCard("Rope"),
                new WeaponCard("Spanner")
        ));
        for (int i = 0; i < allWeapons.size(); i++) {
            System.out.println((i + 1) + ": " + allWeapons.get(i));
        }
        String weaponSuspect = userInput("What weapon did they use?");
        if (allWeapons.contains(new WeaponCard(weaponSuspect))) {
            for (int j = 0; j < allWeapons.size(); j++) {
                if (allWeapons.toString().equals(weaponSuspect)) {
                    return allWeapons.get(j);
                }
            }
        }
        try {
            if (Integer.parseInt(weaponSuspect) >= 1 &&
                    Integer.parseInt(weaponSuspect) <= allWeapons.size()) {
                return allWeapons.get(Integer.parseInt(weaponSuspect) - 1);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Weapon does not exist.");
            return weaponSuspect();
        }
    }

    /**
     * Used for asking questions and waiting for an answer from player
     *
     * @param question What the player is being asked
     * @return The input the user gives
     */

    public static String userInput(String question) {
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void playerArea(Player p) {
        System.out.println(board.printArea(p));
    }

    /**
     * Called at the start of the game to get the number of players
     *
     * @return Number of players in the game
     */

    public int playerStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome! How many players wish to play? (From 3 to 6)");
        String input = sc.nextLine();
        try {
            int players = Integer.parseInt(input);
            if (players < 3 || players > 6)
                throw new Exception();
            return players;
        } catch (Exception e) {
            System.out.println("Please enter a valid number (3 - 6)");
            return playerStart();
        }
    }

    /**
     * Allows players to chose a character to play
     *
     * @param player              The player whose turn it is
     * @param remainingCharacters List of unselected characters
     * @return The name of their selected character
     */

    public String playerSelect(int player, ArrayList<String> remainingCharacters) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player " + player + " who do you want to play?");
        for (int i = 0; i < remainingCharacters.size(); i++)
            System.out.println((i + 1) + ": " + remainingCharacters.get(i));

        String input = sc.nextLine();

        if (remainingCharacters.contains(input))//Checks if the character has already been picked
            return input;

        try {
            int selection = Integer.parseInt(input);
            if (selection >= 1 && selection <= remainingCharacters.size())
                return remainingCharacters.get(selection - 1);
        } catch (Exception e) {
            System.out.println("Please enter a valid character name.");
            return null;
        }
        return null;
    }


    public UI(Board board) {
        this.board = board;
    }

}
