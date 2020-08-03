public class Location {
    private int x;
    private char y;

    public Location(char y, int x){
        this.x = x;
        this.y = y;
    }

    /**
     * @return X location value
     */
    public int getX() { return x;}

    /**
     * @return Y location value
     */
    public char getY() {return y;} //Char to int?

    /**
     * @return Y location value as an integer
     */
    public int getYIndex() { return (int)y-64;}

    /**
     * Sets the X location value
     * @param x
     */
    public void setX(int x) { this.x = x; }

    /**
     * Sets the Y location value
     * @param y
     */
    public void setY(char y) { this.y = y; }

    /**
     * Sets the Y location when given a Y index instead of character value
     * @param y
     */
    public void setY(int y) { this.y = (char)(y+64); }

    /**
     * Moves location 1 step in given direction
     * @param dir   The direction in which the location should move
     * @return      Returns true is move is valid, or false id unsuccessful
     */
    public boolean move(Direction dir){
        //TODO - if (wall in dir) { return false;}
        switch (dir) {
            case NORTH:
                y -= y;
            case SOUTH:
                y += y;
            case EAST:
                x += x;
            case WEST:
                x -= x;
        }
        return true;
    }

    public boolean takeShortcut() {
        //TODO - use teleport tile; return true if valid action, otherwise return false
        return false;
    }

    /**
     * returns true if the location is within a room
     * @return
     */
    public boolean inRoom(Tile[][] board) {
        //TODO - do we need a method here, or in player to return what Room, rather than boolean; or make this return a RoomTile for true, and null for false?
        return (board[getYIndex()][x] instanceof RoomTile);
    }
}
