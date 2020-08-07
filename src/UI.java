import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UI {
    private static Board board;

    //TODO - Questions at the beginning of each turn, where do you want to move?
    //TODO - describeTile(), when you move a tile, describe what is around you
    //TODO - describeRoom(), when you enter a room, describe room name, what weapons and players are already in the room
    //TODO - ask for who you suspect(CONDITION: if player are in a room)
    //TODO - declareAccusation() - the final declaration of who it is with what wep in what room*

    /**
     * String used to draw the entire board
     */
    public static String map =
                    "\n   |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|" +
                    "\n A |//|//|//|//|//|//|//|//|//|mw|//|//|//|//|mg|//|//|//|//|//|//|//|//|//| A" +
                    "\n B |KI|KI|KI|KI|KI|KI|//|__|__|__|BA|BA|BA|BA|__|__|__|//|CO|CO|CO|CO|CO|CO| B" +
                    "\n C |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO| C" +
                    "\n D |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO| D" +
                    "\n E |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|[]|CO|CO|CO|CO|CO| E" +
                    "\n F |KI|KI|KI|KI|KI|KI|__|__|[]|BA|BA|BA|BA|BA|BA|[]|__|__|__|CO|CO|CO|CO|//| F" +
                    "\n G |//|KI|KI|KI|[]|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|__|__|__|__|__|mp| G" +
                    "\n H |__|__|__|__|__|__|__|__|BA|[]|BA|BA|BA|BA|[]|BA|__|__|__|__|__|__|__|//| H" +
                    "\n I |//|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|BI|BI|BI|BI|BI|BI| I" +
                    "\n J |DR|DR|DR|DR|DR|__|__|__|__|__|__|__|__|__|__|__|__|__|[]|BI|BI|BI|BI|BI| J" +
                    "\n K |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI| K" +
                    "\n L |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI| L" +
                    "\n M |DR|DR|DR|DR|DR|DR|DR|[]|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|[]|BI| M" +
                    "\n N |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|__|__|__|__|__|//| N" +
                    "\n O |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|LI|LI|[]|LI|LI|//| O" +
                    "\n P |DR|DR|DR|DR|DR|DR|[]|DR|__|__|CC|CC|CC|CC|CC|__|__|LI|LI|LI|LI|LI|LI|LI| P" +
                    "\n Q |//|__|__|__|__|__|__|__|__|__|CC|CC|CC|CC|CC|__|__|[]|LI|LI|LI|LI|LI|LI| Q" +
                    "\n R |cm|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|LI|LI|LI|LI|LI|LI|LI| R" +
                    "\n S |//|__|__|__|__|__|__|__|__|HA|HA|[]|[]|HA|HA|__|__|__|LI|LI|LI|LI|LI|//| S" +
                    "\n T |LO|LO|LO|LO|LO|LO|[]|__|__|HA|HA|HA|HA|HA|HA|__|__|__|__|__|__|__|__|pp| T" +
                    "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|[]|__|__|__|__|__|__|__|__|//| U" +
                    "\n V |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|[]|ST|ST|ST|ST|ST|ST| V" +
                    "\n W |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST| W" +
                    "\n X |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST| X" +
                    "\n Y |LO|LO|LO|LO|LO|LO|//|ms|//|HA|HA|HA|HA|HA|HA|//|__|//|ST|ST|ST|ST|ST|ST| Y" +
                    "\n   |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|";


    /**
     * A String for a line that's sole purpose its to make things pretty
     */
    public static String separator =
            "****************************************************************************";

    /**
     * A string for of the definition of the general Tiles
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
     * A string printing who the
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
     * prints the map
     */
    public static void displayMap() {
        System.out.println(board.printBoard());
    }


    /**
     * gets they hand(cards player holds) of the player and prints it
     *
     * @param p - the player who's hand is getting accessed
     */
    public static void displayHand(Player p) {
        System.out.println("Your hand contains... \n");
        System.out.println(Arrays.toString(p.getHand().toArray()));
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
     * method for the players turn
     *
     * @param p              - the current player
     * @param moves          - how many moves the player has left
     * @param playerLocation - the players location on the board
     */
    public static void userTurn(Player p, int moves, String playerLocation) {
        System.out.println(playerLocation);
        System.out.println(moves + " moves remaining");
        Scanner sc = new Scanner(System.in);

        //TODO - undo option?

        switch (sc.nextLine().toLowerCase()) {
            case "/help":
                help();
                break;
            case "move":
                p.playerMoves();
                break;
            case "hand":
                displayHand(p);
                break;
            case "suspect":
                //TODO - Add UI and interaction for suspect
                //playerSuspects();
                break;
            case "map":
                displayMap();
                break;
            case "look":
                playerArea(p);
                break;
            case "end":
                p.endTurn();
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
     * @param player The player whose turn it is
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
