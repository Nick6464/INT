import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private static Board board;
    private static Player[] players;
    private Cards.CharacterCard culprit;
    private Cards.WeaponCard murderWeapon;
    private Cards.RoomCard crimeScene;

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
        int handSize = cards.size() / players.length;
        for (int i=0; i < players.length; i++) {
            players[i].
        }
        //TODO - Deal cards to players
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
}
