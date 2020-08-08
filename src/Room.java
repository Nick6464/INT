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

    /**
     * @return  The name of the room
     */
    public String getName() {
        return roomName;
    }

    /**
     * @return  Short name for map display
     */
    public String getInitials() { return initials; }

    /**
     * Sets the shortcut for this room
     * @param destination   - The connected room
     */
    public void setShortcut(Room destination) { shortcutDestination = destination; }

    /**
     * @return  -The room connected via shortcut
     */
    public Room getShortcutDestination() {return shortcutDestination; }

    /**
     * Sets room as the crime scene
     */
    public void setCrimeScene() { isCrimeScene = true; }

    /**
     * @return  -True if room is crime scene
     */
    public boolean isCrimeScene() { return  isCrimeScene; }

    /**
     * @return  -A set of weapons in the room
     */
    public HashSet<Weapon> getPotentialWeapons() { return potentialWeapons; }

    /**
     * @return  -The tiles that make up the room
     */
    public HashSet<RoomTile> getTiles() {return tiles; }
}
