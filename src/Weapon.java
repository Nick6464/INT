public class Weapon {
    private boolean isMurderWeapon = false;
    private final String weaponName;
    private Room location;

    public Weapon(String name) { weaponName = name; }

    public String getName() {return weaponName; }

    public void setMurderWeapon() {isMurderWeapon = true; }

    public boolean isMurderWeapon() { return isMurderWeapon; }

    public void setLocation(Room location) { this.location = location; }

    public void moveWeapon(Room newLocation) {
        location.getPotentialWeapons().remove(this);
        newLocation.getPotentialWeapons().add(this);
    }
}
