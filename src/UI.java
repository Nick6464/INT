import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Board board;

    //TODO - do we want to play in a JWindow, or terminal?
    public void displayBoard() {
        //TODO - make this automated rather than hard coded to update player location
        String b =      "\n   |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|" +
                        "\n A |//|//|//|//|//|//|//|//|//|MW|//|//|//|//|MG|//|//|//|//|//|//|//|//|//|" +
                        "\n B |KI|KI|KI|KI|KI|KI|//|__|__|__|BA|BA|BA|BA|__|__|__|//|Cn|Cn|Cn|Cn|Cn|Cn|" +
                        "\n C |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|Cn|Cn|Cn|Cn|Cn|Cn|" +
                        "\n D |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|Cn|Cn|Cn|Cn|Cn|Cn|" +
                        "\n E |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|[]|Cn|Cn|Cn|Cn|Cn|" +
                        "\n F |KI|KI|KI|KI|KI|KI|__|__|[]|BA|BA|BA|BA|BA|BA|[]|__|__|__|Cn|Cn|Cn|Cn|//|" +
                        "\n G |//|KI|KI|KI|[]|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|__|__|__|__|__|MP|" +
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
                        "\n R |CM|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|LI|LI|LI|LI|LI|LI|LI|" +
                        "\n S |//|__|__|__|__|__|__|__|__|HA|HA|[]|[]|HA|HA|__|__|__|LI|LI|LI|LI|LI|//|" +
                        "\n T |LO|LO|LO|LO|LO|LO|[]|__|__|HA|HA|HA|HA|HA|HA|__|__|__|__|__|__|__|__|PP|" +
                        "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|[]|__|__|__|__|__|__|__|__|//|" +
                        "\n V |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|[]|ST|ST|ST|ST|ST|ST|" +
                        "\n W |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
                        "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
                        "\n V |LO|LO|LO|LO|LO|LO|//|MS|//|HA|HA|HA|HA|HA|HA|//|__|//|ST|ST|ST|ST|ST|ST|";
        System.out.println(b);
    }

    public void playerArea(Player p) {
        //TODO - same as displayBoard(), but instead show a 5x5 ? 7x7 area around current player
    }

    public int playerStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome! How many players wish to play? (From 3 to 6)");
        String input = sc.nextLine();
        try{
            int players = Integer.parseInt(input);
            if (players < 3 || players > 6)
                throw new Exception();
            return players;
        }
        catch (Exception e) {
            System.out.println("Please enter a valid number (3 - 6)");
            return playerStart();
        }
    }

    public String playerSelect(int player, ArrayList<String> remainingCharacters) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player " + player + " who do you want to play?");
        for(int i=0; i < remainingCharacters.size(); i++)
        System.out.println((i+1) + ": " + remainingCharacters.get(i));

        String input = sc.nextLine();

        if (remainingCharacters.contains(input))//Checks if the character has already been picked
           return input;

        try{
            int selection = Integer.parseInt(input);
            if (selection >=1 && selection <= remainingCharacters.size())
                return remainingCharacters.get(selection-1);
        }
        catch (Exception e) {
            System.out.println("Please enter a valid character name.");
            return null;
        }
        return null;
    }

    //STOP BREAKING THINGS NICK

    public UI(Board board) {
        this.board = board;
    }
}
