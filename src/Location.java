public class Location {
    private char x;
    private int y;

    public Location(char x, int y){
        this.x = x;
        this.y = y;
    }

    public char getX() { return x;}

    public int getY() {return y;}

    public boolean move(Direction dir, int dist){
        return true;
    }
}
