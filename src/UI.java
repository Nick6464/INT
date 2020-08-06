import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Board board;

    //TODO - displayMap()
    //TODO - displayCards() - prints what cards you have in your hand
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
            "\n A |//|//|//|//|//|//|//|//|//|mw|//|//|//|//|mg|//|//|//|//|//|//|//|//|//|" +
            "\n B |KI|KI|KI|KI|KI|KI|//|__|__|__|BA|BA|BA|BA|__|__|__|//|CO|CO|CO|CO|CO|CO|" +
            "\n C |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO|" +
            "\n D |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO|" +
            "\n E |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|[]|CO|CO|CO|CO|CO|" +
            "\n F |KI|KI|KI|KI|KI|KI|__|__|[]|BA|BA|BA|BA|BA|BA|[]|__|__|__|CO|CO|CO|CO|//|" +
            "\n G |//|KI|KI|KI|[]|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|__|__|__|__|__|mp|" +
            "\n H |__|__|__|__|__|__|__|__|BA|[]|BA|BA|BA|BA|[]|BA|__|__|__|__|__|__|__|//|" +
            "\n I |//|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|BI|BI|BI|BI|BI|BI|" +
            "\n J |DR|DR|DR|DR|DR|__|__|__|__|__|__|__|__|__|__|__|__|__|[]|BI|BI|BI|BI|BI|" +
            "\n K |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|" +
            "\n L |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|" +
            "\n M |DR|DR|DR|DR|DR|DR|DR|[]|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|[]|BI|" +
            "\n N |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|__|__|__|__|__|//|" +
            "\n O |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|LI|LI|[]|LI|LI|//|" +
            "\n P |DR|DR|DR|DR|DR|DR|[]|DR|__|__|CC|CC|CC|CC|CC|__|__|LI|LI|LI|LI|LI|LI|LI|" +
            "\n Q |//|__|__|__|__|__|__|__|__|__|CC|CC|CC|CC|CC|__|__|[]|LI|LI|LI|LI|LI|LI|" +
            "\n R |cm|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|LI|LI|LI|LI|LI|LI|LI|" +
            "\n S |//|__|__|__|__|__|__|__|__|HA|HA|[]|[]|HA|HA|__|__|__|LI|LI|LI|LI|LI|//|" +
            "\n T |LO|LO|LO|LO|LO|LO|[]|__|__|HA|HA|HA|HA|HA|HA|__|__|__|__|__|__|__|__|pp|" +
            "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|[]|__|__|__|__|__|__|__|__|//|" +
            "\n V |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|[]|ST|ST|ST|ST|ST|ST|" +
            "\n W |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
            "\n X |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
            "\n Y |LO|LO|LO|LO|LO|LO|//|ms|//|HA|HA|HA|HA|HA|HA|//|__|//|ST|ST|ST|ST|ST|ST|";


    /**
     * A String for a line that's sole purpose its to make things pretty
     */
    public static String separator =
            "****************************************************************************";

    /**
     * A string for of the definition of the general Tiles
     */
    public static String helpDef =
            "HELP COMMANDS:"                        +
            "\n     /gt - General Tile definitions" +
            "\n     /st - Spawn Tile definitions"   +
            "\n     /rt - Room Tile definitions"    +
            "\n     /exit - Exits the help menu \n";
    /**
     * A string for of the definition of the general Tiles
     */
    public static String generalDef =
            "GENERAL TILES:"                        +
            "\n     //       - Inaccessible Tiles"  +
            "\n     01 to 24 - X coordinate"        +
            "\n     A to V   - Y coordinate"        +
            "\n     __       - Corridor Tile"       +
            "\n     []       - Door Tile \n";

    /**
     * A string of the definition of the player spawn Tiles
     */
    public static String spawnDef =
            "SPAWN TILES:"                          +
            "\n     mw  - Mrs. White"               +
            "\n     mg  - Mr. Green"                +
            "\n     mp  - Mrs. Peacock"             +
            "\n     pp  - Prof. Plum"               +
            "\n     ms  - Miss Scarlett"            +
            "\n     cm  - Col. Mustard \n";

    /**
     * A string printing who the
     */
    public static String roomDef =
            "ROOM TILES:"                           +
            "\n     KI  - Kitchen"                  +
            "\n     BA  - Ball Room"                +
            "\n     CO  - Conservatory"             +
            "\n     DI  - Dining Room"              +
            "\n     BI  - Billiard Room"            +
            "\n     LI  - Library"                  +
            "\n     LO  - Lounge"                   +
            "\n     HA  - Hall"                     +
            "\n     ST  - Study"                    +
            "\n     CC  - Cellar(Inaccessible) \n";

    /**
     *  prints the map
     *  //TODO - call at the start of every turn
     */
    public static void displayMap() {
        System.out.println(map);
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


    public static String userTurn(int moves, String playerLocation){
        System.out.println(playerLocation);
        System.out.println(moves + " moves remaining");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static String userInput(String question){
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void playerArea(Player p) {
        //TODO - same as displayBoard(), but instead show a 5x5 ? 7x7 area around current player
    }

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


    //https://www.reddit.com/r/learnprogramming/comments/7uou4l/how_to_clear_screen_in_java/
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
