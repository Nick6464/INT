import java.util.ArrayList;
/**
 * Class for the Tiles that are in the corridor(Non-room tiles).
 */
public class CorridorTile implements Tile {

    /**
     * Constructor for CorridorTile
     */
    public CorridorTile() {
    }

    /**
     * Getter for the walls each tile has.
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    @Override
    public ArrayList<Direction> getWalls() {
        return this.walls;
    }
}
