package Cards;

public class WeaponCard implements Card {
    private String weaponName;

    public WeaponCard(String name) {
        weaponName = name;
    }

    public String toString() {
        return "Weapon: " +weaponName;
    }
}
