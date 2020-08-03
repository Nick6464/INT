public enum Direction {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    private String direction;
    Direction(String direction) {
         this.direction = direction;
    }

    @Override
    public String toString(){
        return direction;
    }
}
