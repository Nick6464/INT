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
    ArrayList<Direction> getWalls();


}
