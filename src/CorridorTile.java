import java.util.HashSet;

/**
 * Class for the Tiles that are in the corridor(Non-room tiles).
 */
public class CorridorTile implements Tile {
    private HashSet<Direction> walls = new HashSet<>();
    /**
     * Constructor for CorridorTile
     */
    public CorridorTile() {}


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

    @Override
    public String toString() {
        return "__";
    }
}
