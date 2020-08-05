import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private static Board board;
    private static ArrayList<Player> players;
    private Cards.CharacterCard culprit;
    private Cards.WeaponCard murderWeapon;
    private Cards.RoomCard crimeScene;
    public Boolean running = true;
    private int miss_scarlett = -1;

    String b =
            "\n   |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|" +
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


    /**
     * Adds desired number of players to current game
     */
    private void addPlayers() {
        System.out.println(b); //was testing the display map string
        int playerNumber = UI.playerNumber();
        int i = 1;
        ArrayList<String> allCharacters = new ArrayList<>(Arrays.asList(
                "Miss Scarlett", "Rev Green",
                "Colonel Mustard", "Professor Plum",
                "Mrs. Peacock", "Mrs. White"));
        while (i <= playerNumber) { //Lets all players pick their character
            System.out.println("Player " + i + " who do you want to play?");
            Scanner charScanner = new Scanner(System.in);
            System.out.println(allCharacters);
            String character = charScanner.nextLine();
            if (allCharacters.contains(character)) { //Checks if the character has already been picked
                i++;
                players.add(new Player(character, board)); //Adds the character to the board
                allCharacters.remove(character); //Makes chosen characters unavailable
            } else {
                System.out.println("Please enter a valid character name.");
            }
        }
    }

    /**
     * Creates the relative decks, then combines and shuffles them.
     * Then deals the appropriate number depending on number of players
     */
    public void dealCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(dealWeapons());
        cards.addAll(dealCharacters());
        cards.addAll(dealRooms());
        Collections.shuffle(cards);
        int handSize = cards.size() / players.size();
        for (Player player : players) {
            player.dealHand(cards.subList(0, handSize));
            cards.subList(0, handSize).clear();
        }
        int p = 0;
        while (!cards.isEmpty()) {
            players.get(p++).dealHand(cards.get(0));
        }
    }

    /**
     * Creates a set of weapon cards, shuffles them,
     * and then selects one as the murder weapon for the current game
     *
     * @return weapon card deck
     */
    private ArrayList<Card> dealWeapons() {
        ArrayList<Card> weapons = new ArrayList<>(Arrays.asList(
                new WeaponCard("Candlestick"),
                new WeaponCard("Dagger"),
                new WeaponCard("Lead Pipe"),
                new WeaponCard("Revolver"),
                new WeaponCard("Rope"),
                new WeaponCard("Spanner")
        ));
        Collections.shuffle(weapons);
        murderWeapon = (WeaponCard) weapons.remove(0);
        return weapons;
    }

    /**
     * Creates a set of character cards, shuffles them,
     * and then selects one as the murderer for the current game
     *
     * @return character card deck
     */
    private ArrayList<Card> dealCharacters() {
        ArrayList<Card> characters = new ArrayList<>(Arrays.asList(
                new CharacterCard("Miss Scarlett"),
                new CharacterCard("Rev Green"),
                new CharacterCard("Colonel Mustard"),
                new CharacterCard("Professor Plum"),
                new CharacterCard("Mrs. Peacock"),
                new CharacterCard("Mrs. White")
        ));
        Collections.shuffle(characters);
        culprit = (CharacterCard) characters.remove(0);
        return characters;
    }

    /**
     * Creates a set of room cards, shuffles them,
     * and then selects one as the crime scene for the current game
     *
     * @return room card deck
     */
    private ArrayList<Card> dealRooms() {
        ArrayList<Card> rooms = new ArrayList<>(Arrays.asList(
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
        Collections.shuffle(rooms);
        crimeScene = (RoomCard) rooms.remove(0);
        return rooms;
    }

    /**
     * Main game loop once all set up is complete
     */
    private void gameLoop() {
        int playerTurn;
        if (miss_scarlett >= 0) {        //Miss Scarlett always starts, so sets her turn first
            playerTurn = miss_scarlett; //if she is in the game, otherwise player 1 starts.
            // Double check, there may be a set player order. Can make a comparison class to sort players in order of character
        } else {
            playerTurn = 0;
        }
        while (running) { //Possibly cleaner to write while(true), with a break when GameOver instead of using variable
            Player currentPlayer = players.get(playerTurn);
            currentPlayer.takeTurn();
            playerTurn++;
            if (playerTurn >= players.size()) { //Resets the turn to player 1 after all have gone
                playerTurn = 0;
            }
        }
    }


    /**
     * Constructor for Game
     */
    public Game() {
        board = new Board();
        players = new ArrayList<>();
        addPlayers();
        dealCards();
        board.getRooms().get(crimeScene.getRoomName()).setCrimeScene();


        //Testing move and UI function with Miss Scarlett//
        Player p = players.get(0);
        p.takeTurn();
        p.move(Direction.NORTH, 1);
        p.move(Direction.WEST, 2);
        p.move(Direction.NORTH, 4);
        p.move(Direction.WEST, 1);
        p.takeTurn();
        //                              //

        //gameLoop(); currently and infinite loop
    }


    public static void main(String[] args) {
        new Game();
    }
}
