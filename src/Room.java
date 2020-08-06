import java.util.HashSet;

public class Room {
    private boolean isCrimeScene = false;
    private final String roomName;
    private HashSet<Weapon> potentialWeapons;

    public Room(String name) {
        roomName = name;
        potentialWeapons = new HashSet<>();
    }

    public String getName() {
        return roomName;
    }

    public void setCrimeScene() { isCrimeScene = true; }

    public boolean isCrimeScene() { return  isCrimeScene; }

    public HashSet<Weapon> getPotentialWeapons() { return potentialWeapons; }
}
