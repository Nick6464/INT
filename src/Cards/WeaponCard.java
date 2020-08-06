package Cards;

public class WeaponCard implements Card {
    private final String weaponName;

    public WeaponCard(String name) {
        weaponName = name;
    }

    public String getWeaponName() { return weaponName; }

    public String toString() {
        return "[Weapon: " + weaponName + "]";
    }

    @Override
    public int compareTo(Object o) {
        return toString().compareTo(o.toString());
    }
}
