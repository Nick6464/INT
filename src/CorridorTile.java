import java.util.ArrayList;
/**
 * Class for the Tiles that are in the corridor(Non-room tiles).
 */
public class CorridorTile implements Tile {

    private final ArrayList<Direction> walls;

    /**
     * Constructor for CorridorTile
     * @param walls
     */
    public CorridorTile(ArrayList<Direction> walls) {
        this.walls = walls;
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
