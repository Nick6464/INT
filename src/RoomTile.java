import java.util.TreeSet;

/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private final Room room;
    private boolean isDoorway = false;
    private TreeSet<Direction> walls = new TreeSet<>();
    private boolean occupied = false;
    private Player player;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room) {
        this.room = room;
    }

    public void setDoorway() {isDoorway = true; }

    public boolean isDoorway() {return isDoorway;}

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public void setOccupied(Player player) {
        occupied  = true;
        this.player = player;
    }

    @Override
    public void setVacant() {
        occupied = false;
        player = null;
    }

    /**
     * Getter for the walls each tile has
     *
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    @Override
    public TreeSet<Direction> getWalls() { return walls; }

    /**
     * Adds a wall to the tile
     *
     * @param dir The direction in which travel is blocked by a wall
     */
    @Override
    public void setWall(Direction dir) { walls.add(dir); }

    /**
     * Getter for RoomTile
     * @return room the tile belongs to
     */
    public Room getRoom() {
        return this.room;
    }

    @Override
    public String toString() {
        if (isDoorway)
            return "[]";
        if(occupied)
            return player.initials;
        return room.getInitials();
    }
}
