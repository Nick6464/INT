import java.util.HashSet;

public class Room {
    private boolean isCrimeScene = false;
    private final String roomName;
    private final String initials;
    private HashSet<Weapon> potentialWeapons;
    private HashSet<RoomTile> tiles;
    public final boolean hasShortcut;
    private Room shortcutDestination;
    private HashSet<RoomTile> doorways;

    public Room(String name, String initials, boolean hasShortcut) {
        roomName = name;
        this.initials = initials;
        this.hasShortcut = hasShortcut;
        tiles = new HashSet<>();
        doorways = new HashSet<>();
        potentialWeapons = new HashSet<>();
    }

    public String getName() {
        return roomName;
    }

    public String getInitials() { return initials; }

    public void setShortcut(Room destination) { shortcutDestination = destination; }

    public Room getShortcutDestination() {return shortcutDestination; }

    public void setCrimeScene() { isCrimeScene = true; }

    public boolean isCrimeScene() { return  isCrimeScene; }

    public HashSet<Weapon> getPotentialWeapons() { return potentialWeapons; }
}
