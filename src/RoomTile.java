import java.util.ArrayList;
/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private Room room;

    /**
     * Constructor for RoomTile
     * - room: The Room the tile belongs in
     */
    public RoomTile(Room room) {
        this.room = room;
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
