import java.util.TreeSet;

/**
 * Class for the Tiles that are in the corridor(Non-room tiles).
 */
public class CorridorTile implements Tile {
    private TreeSet<Direction> walls = new TreeSet<>();
    public boolean occupied = false;
    private Player player;

    /**
     * Constructor for CorridorTile
     */
    public CorridorTile() {}

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

    /**
     * Getter for the walls each tile has
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    @Override
    public TreeSet<Direction> getWalls() { return walls; }

    /**
     * Adds a wall to the tile
     * @param dir   The direction in which travel is blocked by a wall
     */
    @Override
    public void setWall(Direction dir) { walls.add(dir); }

    /**
     * Sting used for displaying tiles in map
     * @return  - 2char String representation of tile
     */
    @Override
    public String toString() {
        if (occupied)
            return player.initials;
        return "__";
    }
}
