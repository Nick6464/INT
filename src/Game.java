import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private static UI UI;
    private static Board board;
    private static ArrayList<Player> players;
    private Cards.CharacterCard culprit;
    private Cards.WeaponCard murderWeapon;
    private Cards.RoomCard crimeScene;
    public Boolean running = true;
    private int miss_scarlett = -1;

    /**
     * Adds desired number of players to current game
     */
    private void addPlayers() {
        int numPlayers = UI.playerStart();

        int i = 1;
        ArrayList<String> characters = new ArrayList<>(Arrays.asList(
                "Miss Scarlett", "Rev Green",
                "Colonel Mustard", "Professor Plum",
                "Mrs. Peacock", "Mrs. White"));

        while (i <= numPlayers){ //Lets all players pick their character
            String character = UI.playerSelect(i, characters);

            if (character == null)
                continue;

            characters.remove(character);
            players.add(new Player(character, board)); //Adds the character to the board
            i++;
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
    private void gameLoop(){
        int playerTurn;
        if (miss_scarlett >= 0){        //Miss Scarlett always starts, so sets her turn first
            playerTurn = miss_scarlett; //if she is in the game, otherwise player 1 starts.
            // Double check, there may be a set player order. Can make a comparison class to sort players in order of character
        } else {
            playerTurn = 0;
        }
        while (running){ //Possibly cleaner to write while(true), with a break when GameOver instead of using variable
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
        UI = new UI(board);
        players = new ArrayList<>();
        addPlayers();
        dealCards();
        board.getRooms().get(crimeScene.getRoomName()).setCrimeScene();

        //Testing move and UI function with Miss Scarlett//
        Player p = players.get(0);
        p.takeTurn();
        p.move(Direction.NORTH,1);
        p.move(Direction.WEST,2);
        p.move(Direction.NORTH,4);
        p.move(Direction.WEST,1);
        p.takeTurn();
        //                              //

        //gameLoop(); currently and infinite loop
    }


    public static void main(String[] args) {
        new Game();
    }
}
