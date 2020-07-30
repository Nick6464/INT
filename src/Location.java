public class Location {
    private int x;
    private char y;

    public Location(char y, int x){
        this.x = x;
        this.y = y;
    }

    public int getX() { return x;}

    public char getY() {return y;} //Char to int?

    public void setX(int x) { this.x = x; }

    public void setY(char y) { this.y = y; }

    public boolean move(Direction dir){
        //if (wall in dir) { return false;}
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

    /**
     * returns true if the location is within a room
     * @return
     */
    public boolean inRoom() {
        return true; // temp line
    }
}
