package Cards;

public class CharacterCard implements Card {
    private final String characterName;

    public CharacterCard(String characterName) {
        this.characterName  = characterName;
    }

    public String toString() {
        return "[Character: " + characterName + "]";
    }

    @Override
    public int compareTo(Object o) {
        return toString().compareTo(o.toString());
    }
}
