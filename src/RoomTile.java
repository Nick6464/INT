/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private final Room room;
    private RoomTile shortcutExit = null;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room) {
        this.room = room;
    }

    /**
     *
     * @param shortcutExit
     */
    public void addShortcut(RoomTile shortcutExit) {
        this.shortcutExit = shortcutExit;
    }

    /**
     * Getter for RoomTile
     * @return room the tile belongs to
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Checks if there is a shortcut on this tile
     * @return true is a shortcut exists
     */
    public boolean secretEntrance() {
        return !(shortcutExit == null);
    }

    /**
     * Returns the shortcut exit tile
     * @return  shortcutExit
     */
    public RoomTile secretExit() {
        return shortcutExit;
    }
}
