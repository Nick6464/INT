import java.util.HashSet;

/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private final Room room;
    private HashSet<Direction> walls = new HashSet<>();
    private RoomTile shortcutExit = null;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room) {
        this.room = room;
    }

    /**
     * Getter for the walls each tile has
     *
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    @Override
    public HashSet<Direction> getWalls() { return walls; }

    /**
     * Adds a wall to the tile
     *
     * @param dir The direction in which travel is blocked by a wall
     */
    @Override
    public void setWall(Direction dir) { walls.add(dir); }

    /**
     * Adds a shortcut to a tile
     * @param shortcutExit  - the exit tile of the shortcut
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
