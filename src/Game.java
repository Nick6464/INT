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

    public void dealCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(dealWeapons());
        cards.addAll(dealCharacters());
        cards.addAll(dealRooms());
        Collections.shuffle(cards);
    }

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

    private ArrayList<Card> dealCharacters() {
        ArrayList<Card> characters = new ArrayList<>();
        Collections.shuffle(characters);
        culprit = (CharacterCard) characters.remove(0);
        return characters;
    }

    private ArrayList<Card> dealRooms() {
        ArrayList<Card> rooms = new ArrayList<>();
        Collections.shuffle(rooms);
        crimeScene = (RoomCard) rooms.remove(0);
        return rooms;
    }
}
