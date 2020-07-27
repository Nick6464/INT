package Cards;

public class CharacterCard implements Card {
    private String characterName;

    public CharacterCard(String characterName) {
        this.characterName  = characterName;
    }

    public String toString() {
        return "Character: " + characterName;
    }
}
