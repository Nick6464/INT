public class Room {
    private boolean isCrimeScene = false;
    private final String roomName;

    public Room(String name) {
        roomName = name;
    }

    public String getName() {
        return roomName;
    }

    public void setCrimeScene() { isCrimeScene = true; }

    public boolean isCrimeScene() { return  isCrimeScene; }
}
