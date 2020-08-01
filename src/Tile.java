import java.util.ArrayList;
public interface Tile {

    /**
     * walls
     * each tile has an ArrayList of Directions, the directions are where walls are (directions you cant move)
     */
    ArrayList<Direction> walls = new ArrayList<>();

    /**
     * Getter for the walls each tile has
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    default ArrayList<Direction> getWalls() {
        return walls;
    }

    /**
     * Adds a wall to the tile
     * @param dir   The direction in which travel is blocked by a wall
     */
    default void setWall(Direction dir) {
        walls.add(dir);
    };
}
