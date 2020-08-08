import java.util.TreeSet;

public interface Tile {
    /**
     * Getter for the walls each tile has
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    TreeSet<Direction> getWalls();

    /**
     * Adds a wall to the tile
     * @param dir   The direction in which travel is blocked by a wall
     */
     void setWall(Direction dir);

    /**
     * Checks if tile is occupied by a player
     * @return - Boolean occupied
     */
     boolean isOccupied();

    /**
     * Sets the tile to be occupied by player
     * @param player    - The player occupying the tile
     */
     void setOccupied(Player player);

    /**
     * Sets the tile to be vacant, and clears player from it
     */
    void setVacant();

}
