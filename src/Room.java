import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Room {
    private boolean isCrimeScene = false;
    private final String roomName;
    private final String initials;
    private HashSet<Weapon> potentialWeapons;
    private HashSet<RoomTile> tiles;
    public final boolean hasShortcut;
    private Room shortcutDestination;
    private HashMap<RoomTile, Direction> doorways;

    public Room(String name, String initials, boolean hasShortcut) {
        roomName = name;
        this.initials = initials;
        this.hasShortcut = hasShortcut;
        tiles = new HashSet<>();
        doorways = new HashMap<>();
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

    public void setTiles(RoomTile rt) { tiles.add(rt); }

    public void setDoorways(RoomTile rt, Direction dir) {
        doorways.put(rt,dir);
    }

    public Set<RoomTile> getDoorways() {
        return doorways.keySet();
    }

    /**
     * Walks the player 1 space outside the room via chosen exit
     * @param rt    Tile corresponding to chosen doorway
     * @param p     The player leaving the room
     */
    public void leaveRoom(RoomTile rt, Player p) {
        for (RoomTile tile : tiles)
            if (tile.getOccupant() == p) {
                tile.setVacant();
                break;
            }
        p.getLocation().setX(rt.getLocation().getX());
        p.getLocation().setY(rt.getLocation().getY());

        p.move(doorways.get(rt), 1);
    }

    //TODO Show other players who are in the room

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (RoomTile tile : tiles)
            if (tile.isOccupied())
                players.add(tile.getOccupant());
        return players;
    }

    /**
     * @return A vacant tile in the room that does not block a doorway
     */
    public RoomTile enterRoom() {
        for (RoomTile tile : tiles) {
            if(tile.isDoorway() || tile.isOccupied())
                continue;
            return tile;
        }
        return null;
    }

    @Override
    public String toString() {
        return roomName;
    }
}
