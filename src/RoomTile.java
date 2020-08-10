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
    private Location location;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room) {
        this.room = room;
    }

    /**
     * Sets tile to be a doorway into a room
     */
    public void setDoorway(Location loc) {
        isDoorway = true;
        location = loc;
        }

    public Location getLocation(){
        return location;
    }

    /**
     * Returns true if tile is a doorway
     * @return  - Boolean isDoorway
     */
    public boolean isDoorway() {return isDoorway;}

    /**
     * Checks if tile is occupied by a player
     * @return - Boolean occupied
     */
    @Override
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets the tile to be occupied by player
     * @param player    - The player occupying the tile
     */
    @Override
    public void setOccupied(Player player) {
        occupied  = true;
        this.player = player;
    }

    /**
     * Sets the tile to be vacant, and clears player from it
     */
    @Override
    public void setVacant() {
        occupied = false;
        player = null;
    }


    //TODO Get players in a room
    public Player getOccupant() { return player;}

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

    /**
     * Sting used for displaying tiles in map
     * @return  - 2char String representation of tile
     */
    @Override
    public String toString() {
        if (isDoorway)
            return "[]";
        if(occupied)
            return player.initials;
        return room.getInitials();
    }
}
