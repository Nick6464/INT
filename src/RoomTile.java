import java.util.ArrayList;
/**
 * Class for the Tiles that are in rooms.
 */
public class RoomTile implements Tile {
    private final Room room;

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

}
