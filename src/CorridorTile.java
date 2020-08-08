import java.util.TreeSet;

/**
 * Class for the Tiles that are in the corridor(Non-room tiles).
 */
public class CorridorTile implements Tile {
    private TreeSet<Direction> walls = new TreeSet<>();
    public boolean occupied = false;
    private Player player;

    /**
     * Constructor for CorridorTile
     */
    public CorridorTile() {}

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public void setOccupied(Player player) {
        occupied  = true;
        this.player = player;
    }

    @Override
    public void setVacant() {
        occupied = false;
        player = null;
    }

    /**
     * Getter for the walls each tile has
     *
     * @return an ArrayList of Directions, the directions represent where walls are.
     */
    @Override
    public TreeSet<Direction> getWalls() { return walls; }

    /**
     * Adds a wall to the tile
     *
     * @param dir The direction in which travel is blocked by a wall
     */
    @Override
    public void setWall(Direction dir) { walls.add(dir); }

    @Override
    public String toString() {
        if (occupied)
            return player.initials;
        return "__";
    }
}
