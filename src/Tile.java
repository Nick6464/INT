import java.util.HashSet;

public interface Tile {
    /**
     * Getter for the walls each tile has
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    HashSet<Direction> getWalls();

    /**
     * Adds a wall to the tile
     * @param dir   The direction in which travel is blocked by a wall
     */
     void setWall(Direction dir);
}
