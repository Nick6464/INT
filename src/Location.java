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
     * @param x - X axis value
     */
    public void setX(int x) { this.x = x; }

    /**
     * Sets the Y location value
     * @param y - Y axis value
     */
    public void setY(char y) { this.y = y; }

    /**
     * Sets the Y location when given a Y index instead of character value
     * @param y - Y axis value
     */
    public void setY(int y) { this.y = (char)(y+64); }

    /**
     * Moves location 1 step in given direction
     * @param dir   The direction in which the location should move
     * @return      Returns true is move is valid, or false id unsuccessful
     */
    public boolean move(Direction dir, Board board){
        if (board.getTile(getYIndex(), x).getWalls().contains(dir))
            return false;
        switch (dir) {
            case NORTH  ->  {if(board.getTile(getYIndex()-1,x).isOccupied())
                                return false;
                                y -= 1;}
            case SOUTH  ->  {if(board.getTile(getYIndex()+1,x).isOccupied())
                                return false;
                            y += 1;}
            case EAST   ->  {if(board.getTile(getYIndex(),x+1).isOccupied())
                                return false;
                            x += 1;}
            case WEST   ->  {if(board.getTile(getYIndex(),x-1).isOccupied())
                                return false;
                            x -= 1;}
        }
        return true;
    }

    /**
     * Moves player through shortcut to other room
     * @return  -True is method was successful
     */
    public boolean takeShortcut() {
        //TODO - use teleport tile; return true if valid action, otherwise return false **May be moved to Room.class**
        return false;
    }

    public String toString() {
        return "[" + y + ", " + x + "]";
    }
}
