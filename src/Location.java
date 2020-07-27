public class Location {
    private int x;
    private char y;

    public Location(int x, char y){
        this.x = x;
        this.y = y;
    }

    public int getX() { return x;}

    public char getY() {return y;} //Char to int?

    public boolean move(Direction dir, int dist){
        for (int i = 0; i < dist; i++) {
            switch (dir) {
                case NORTH:
                case SOUTH:

                case EAST:
                case WEST:
            }
        }
        return false;
    }
}
