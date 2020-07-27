import java.util.ArrayList;
/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private final Room room;
    private final ArrayList<Direction> walls;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room, ArrayList<Direction> walls) {
        this.room = room;
        this.walls = walls;
    }

    /**
     * Getter for RoomTile
     * @return room the tile belongs to
     */
    public Room getRoom() {
        return this.room;
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
